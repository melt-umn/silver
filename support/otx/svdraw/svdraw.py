import os

cache = {}

print('\n\n\nDrawing...')

def translate(x):
	# print(x)
	def _translate(x):
		if x[1] in ("!Integer", "!String", "!Float", "!Boolean"):
			return x[2]
		if x[1] == "!List":
			return list(map(translate, x[2]))
		if x[1] == "!Terminal":
			locnt = translate(x[4])
			fakent = NT([x[0], x[2].split(":")[-1]+"<"+x[3]+">", [], []])
			fakent.origin = locnt
			fakent.originlabel = "parsed-from"
			return fakent
		# if x[1].split(":")[-1].startswith("otxLink"):
		# 	return translate(x[2][0])
		return NT(x)
	if x[0] in cache:
		return cache[x[0]]
	else:
		r = _translate(x)
		cache[x[0]] = r
		return r

class NT:
	def __init__(self, record):
		self.ids = record[0]
		self.name = record[1]
		self.children = list(map(translate, record[2]))
		self.childids = list(map(lambda x:x[0], record[2]))
		self.children_all_primitive = all(map(lambda x:not isinstance(x, NT), self.children))\
		 and 1 #Change to show/hide primitives

		self.origin = None
		o = list(filter(lambda x:x[0].endswith("otxinfo"), record[3]))
		if o != []:
			o = translate(o[0][1])
			self.origin, self.originlabel = o, ""

	def __repr__(self):
		return self.name.split(":")[-1] + "(" + ",".join(map(repr, self.children)) + ")"

	def smartrepr(self):
		return self.name.split(":")[-1] + "(" + ",".join(map(lambda x: repr(x) if type(x)!=NT else "_", self.children)) + ")"

while not input().startswith("origin"): pass

false = False
true = True
sxstart = translate(eval(input().replace("sxstart: ","",1)))
sxres   = translate(eval(input().replace("sxres  : ","",1)))

os.chdir("/home/louis/School/melt/origintracking/silver/support/otx/svdraw")

with open("out.dot", 'w') as fd:
	w = lambda x: fd.write(x+"\n")
	w("digraph{")
	for k, v in cache.items():
		# TODO: FIX
		if (isinstance(v, NT) and any(map(lambda x:x in v.name, ('just', 'nothing', 'pair', 'Note')))) or type(v) in [str, int, bool, list]:
			continue

		if isinstance(v, NT):
			# if v.children_all_primitive:
			# 	name = repr(v)
			# else:
			# 	name = v.name.split(":")[-1]
			name = v.smartrepr()
		else:
			name = repr(v)

		w("n"+str(k)+" [label=\""+name+"\"")
		if v is sxstart and not v is sxres:
			w(" color=red penwidth=3")
		if v is sxres and not v is sxstart:
			w(" color=blue penwidth=3")
		if v is sxres and v is sxstart:
			w(" color=purple penwidth=3")
		w("];")

		if isinstance(v, NT):
			if v.origin:
				w("n"+str(k)+" -> n"+str(v.origin.ids)+" [style=dashed, label=\""+v.originlabel+"\"];")

			for i in range(len(v.children)):
				if type(v.children[i]) not in [int, bool, str, list]:
					w("n"+str(k)+" -> n"+str(v.childids[i])+" [label=\""+str(i)+"\"];")
	w("}")

os.system("dot -Tpng -o out.png out.dot")