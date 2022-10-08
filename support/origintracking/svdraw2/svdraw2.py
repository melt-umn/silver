import os, sys

allow_c = "--allow_c" in sys.argv
do_partial_redex_prop = "--do_partial_redex_prop" in sys.argv
allow_multiredex = "--allow_multiredex" in sys.argv
strip_packagename = "--strip_packagename" in sys.argv
small_nodes = "--all_oi" not in sys.argv

cache={}
def translate(x):
	def _translate(x):
		if len(x) == 1:
			return PrimitiveValue("???", str(x[0]))
		if x[1] in ("!Integer", "!String", "!Float", "!Boolean"):
			return PrimitiveValue(x[0], x[2])
		if x[1] == "???":
			return PrimitiveValue(x[0], "?"+repr(x[2:])+"?")
		if x[1] == "!List":
			return PrimitiveList(x[0], list(map(translate, x[2])))
		if x[1] == "!Null":
			return PrimitiveValue(x[0], None)
		if x[1] == "!Terminal":
			return Token(x)
		if x[1] == "silver:core:loc":
			return LocationNT(x)
		# if x[1].endswith("OIT"):
		# 	return PrimitiveValue(x[0], "<"+x[1]+">")
		return NT(x)

	if x[0] in cache:
		return cache[x[0]]
	else:
		r = _translate(x)
		cache[x[0]] = r
		return r

class SilverValue:
	def has_as_child(self, x):
		return False

	def is_origins_impl_value(self):
		return False

	def node_color(self):
		return None

class ComplexValue(SilverValue):
	pass

class NT(ComplexValue):
	def __init__(self, pexpr):
		self.ids = pexpr[0]
		self.name = pexpr[1]
		self.children = list(map(translate, pexpr[2]))

		self.origin = None
		self.redex = None
		self.newly_introduced = True
		o = translate(pexpr[4])
		self.oi = o
		self.comment = o.node_text(False)
		if o is None or (isinstance(o, PrimitiveValue) and o.value is None):
			return
		if o.name=="silver:core:otherOriginInfo" or o.name=="silver:core:parsedOriginInfo":
			return
		# print()
		# print(self.name)
		# print(o.children[2].value)
		self.origin, self.originlabel = o.children[1],\
			"\n".join(map(lambda x:x.node_text(inclo=False), o.children[2].value))
		if len(o.children)>4:
			self.redex, self.redexlabel = o.children[3],\
				"\n".join(map(lambda x:x.node_text(inclo=False), o.children[4].value))
		self.newly_introduced = o.children[-1].value

	def get_real_children(self):
		return list(filter(lambda x:not isinstance(x, PrimitiveValue), self.children))

	def has_as_child(self, x):
		return x in self.children or any(map(lambda c:c.has_as_child(x), self.children))

	def node_text(self, inclo=True):
		# r=self.name.split(":")[-1]+"("
		if self.name=="silver:core:originDbgNote":
			return ":".join(self.children[0].value[len("silver:core:loc("):].replace("\"","").replace(" ","").split(",")[0:3])
		r=(self.name.split(":",1)[1] if strip_packagename else self.name)+"("
		for c in self.children:
			if isinstance(c, PrimitiveValue) or isinstance(c, LocationNT):
				r+=c.node_text(inclo=False)
			else:
				r+="_"
			if c!=self.children[-1]:
				r += ","
		r+=")"
		if inclo:
			if small_nodes:
				if isinstance(self.oi, NT):
					r+="\\n"+{
						"silver:core:originAndRedexOriginInfo": "OR",
						"silver:core:originOriginInfo": "O",
						"silver:core:parsedOriginInfo": "P",
						"silver:core:otherOriginInfo":"X"}[self.oi.name]
					r+=";"+{
						"silver:core:setInGlobalOIT": "G",
						"silver:core:setAtConstructionOIT": "C",
						"silver:core:setAtAccessOIT": "A",
						"silver:core:setAtForwardingOIT": "F",
						"silver:core:setFromParserOIT": "P",
						"silver:core:setFromEntryOIT": "E",
						"silver:core:setFromReflectionOIT": "R",
						"silver:core:setFromReificationOIT": "I",
						"silver:core:setAtNewOIT": "N"}[self.oi.children[0].name]
				else:
					r+="\\n"+"P"+";"+self.oi.node_text(inclo=False)
			else:
				r+="\\n"+self.comment
		return r

	def is_origins_impl_value(self):
		return any(x in self.name for x in ["OriginInfo", "loc", "originLink", "Note"]) or self.name.endswith("OIT")

class LocationNT(NT):
	def node_text(self, inclo=True):
		return "loc("+":".join(map(lambda x:str(x.value), self.children[:2]))+")"

class Token(ComplexValue):
	def __init__(self, pexpr):
		self.ids = pexpr[0]
		self.name = pexpr[2]
		self.lexeme = pexpr[3]
		self.loc = translate(pexpr[4])
		self.origin = None
		self.redex = None
		self.newly_introduced = True

	def node_text(self, inclo=True):
		return self.name.split(":")[-1]+"<"+repr(self.lexeme).replace("\"","\\\"")+">\n"+self.loc.node_text()

	def node_color(self):
		return "grey"

class PrimitiveValue(SilverValue):
	def __init__(self, ids, value):
		self.ids = ids
		self.value = value
		self.origin = None
		self.redex = None
		self.newly_introduced = False

	def node_text(self, inclo=True):
		return repr(self.value).replace("\"","\\\"")

class PrimitiveList(PrimitiveValue):
	def has_as_child(self, x):
		return x in self.value or any(map(lambda c:c.has_as_child(x), self.value))

	def node_text(self, inclo=True):
		return "["+", ".join(map(lambda x:x.node_text(), self.value))+"]"

def primitive(x):
	return isinstance(x, PrimitiveValue)
def notprimitive(x):
	return not isinstance(x, PrimitiveValue)

while True:
	x=input()
	if x == "---SVDRAW2 START---": break
	print(">", x)

true, false = True, False
start = eval(input())
end = eval(input())
assert input()=="---SVDRAW2 END---"

print("\n"*3)

print("evaling pexprs...")
start = translate(start)
end = translate(end)

os.chdir(os.path.dirname(__file__))

fd = open("out.dot", 'w')
w = lambda x: fd.write(x+"\n")
w("digraph{")
w("node [fontname = \"monospace\"];")
w("graph [compound=true ")
# w("rankdir=TB ")
w("splines=spline];")

print("finding roots...")
# roots = list(filter(lambda x:all(map(lambda o:not o.has_as_child(x), cache.values())) and not x.is_origins_impl_value(),
# 	cache.values()))
roots = []
for v in cache.values():
	if not v.is_origins_impl_value():
		for o in cache.values():
			if not o.is_origins_impl_value():
				if o.has_as_child(v):
					break
		else:
			if isinstance(v, ComplexValue) and (allow_c or not v.name.endswith("_c")):
				roots.append(v)

def draw_only_children(node, color=None):
	if node is start: color="#bbbbff"
	if node is end: color="#bbffbb"
	effcolor = node.node_color() or color or "white"
	w("n"+str(node.ids)+" [label=\""+node.node_text()+"\" style=filled fillcolor=\""+effcolor+"\"")
	if node is start or node is end:
		w(" penwidth=3 ")
	if node.newly_introduced:
		w(" shape=diamond ")
	w("];")
	if not isinstance(node, NT): return
	w("subgraph children"+str(node.ids)+"{")
	kids = node.get_real_children()
	for child in kids:
		if not (allow_c or not node.name.endswith("_c")): continue
		draw_only_children(child, color)
		w("n"+str(node.ids)+" -> n"+str(child.ids)+" [label="+str(node.children.index(child))+" arrowhead=none];")
		if child!=kids[-1]:
			next = kids[kids.index(child)+1]
			w("n"+str(node.ids)+" -> n"+str(child.ids)+" [style=invisible arrowhead=none];")
	w("}")

print("drawing object graph...")

for thing in filter(lambda x:isinstance(x, ComplexValue), roots):
	w("subgraph cluster"+str(thing.ids)+"{")
	draw_only_children(thing)
	w("}")

def propogate_redex(node, redex_haver, root=True):
	if do_partial_redex_prop: w("n"+str(node.ids)+" -> n"+str(redex_haver.ids)+" [style=dotted penwidth=6 arrowsize=0.25 ];")
	w("n"+str(node.ids)+" -> n"+str(redex_haver.redex.ids)+" [style=dotted, label=\""+(redex_haver.redexlabel if root or allow_multiredex else "")+"\"];")
	if isinstance(node, NT):
		for x in node.get_real_children():
			if x.redex is None or allow_multiredex:
				if do_partial_redex_prop: propogate_redex(x, redex_haver, False)

print("adding origin information...")

for thing in cache.values():
	if thing.origin:
		if not (allow_c or not thing.origin.name.endswith("_c")) or thing.is_origins_impl_value(): continue
		w("n"+str(thing.ids)+" -> n"+str(thing.origin.ids)+" [style=dashed, label=\""+thing.originlabel+"\"];")

	if thing.redex:
		if not (allow_c or not thing.origin.name.endswith("_c")): continue
		propogate_redex(thing, thing)


w("}")

fd.close()

print("rendering...")

os.system("dot -Tsvg -o out.svg out.dot")