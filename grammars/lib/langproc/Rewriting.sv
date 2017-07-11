grammar lib:langproc ;

{- Version 1.

   To examine decorated trees we must instantiate typevar a to be a
   Decorated NT.  But this means that the helper function
   applyRewriteRule returns a decorated tree which most likely needs
   to be converted to the undecorated version to be used in
   constructing a new tree.

   This version is also incorrect - if no rewrite is done at this
   level then the rewrites done at a lower level are not included...

-}
{-
nonterminal RewriteRule_v1<a> ;

synthesized attribute match_v1 :: Boolean occurs on RewriteRule_v1<a> ;
synthesized attribute rewrite_v1<a> :: a occurs on RewriteRule_v1<a> ;

inherited attribute tree_v1<a> :: a occurs on RewriteRule_v1<a> ;

function applyRewriteRule_v1
a ::= rr::RewriteRule_v1<a> t::a
{ return if rr.match_v1 then rr.rewrite_v1 else t ;
  rr.tree_v1 = t ;
}
-}

{- Version 2.

   Use two parameters, one for the decorated tree to examine for a
   match and one for the undecorated version to return if there is no
   match.

   But now applyRewriteRule_v1 will not type-check since we can't
   "new" the passed in decorated tree.

   Instead we pass in as inh attrs the decorated and undecorated versions.

-}
{-
nonterminal RewriteRule_v2<dt t> ;

synthesized attribute match_v2 :: Boolean occurs on RewriteRule_v2<dt t> ;
synthesized attribute rewrite_v2<dt t> :: t occurs on RewriteRule_v2<dt t> ;

inherited attribute dtree_v2<dt t> :: dt occurs on RewriteRule_v2<dt t> ;
inherited attribute tree_v2<dt t> :: t occurs on RewriteRule_v2<dt t> ;

function applyRewriteRule_v2
t ::= rr::RewriteRule_v2<dt t> dtree::dt tree::t
{ return if rr.match_v2 then rr.rewrite_v2 else tree ;
  rr.tree_v2 = tree ;
  rr.dtree_v2 = dtree ;
}

function applyARewriteRule
t ::= rrs::[RewriteRule_v2<dt t>] tomatch::dt nomatch::t
{ return if   null(rrs)
         then nomatch
         else 
         if   first_rr.match_v2 
         then first_rr.rewrite_v2 
         else applyARewriteRule ( tail(rrs), tomatch, nomatch) ;

  local attribute first_rr :: RewriteRule_v2<dt t> ;
  first_rr = head(rrs);
  first_rr.dtree_v2 = tomatch ;
  first_rr.tree_v2 = nomatch ;
}

function applyAllRewriteRules
t ::= --rrs::[RewriteRule_v2<dt t>]
      rr::RewriteRule_v2<dt t>
      tomatch::dt nomatch::t
{ return if   null(rrs)
         then nomatch
         else --nomatch ;
              applyAllRewriteRules ( rr, -- tail(rrs),
                   tomatch, first_rr.rewrite_v2) ;

  local attribute rrs::[RewriteRule_v2<dt t>] ;
  rrs = [rr] ;
  local attribute first_rr :: RewriteRule_v2<dt t> ;
  first_rr = head(rrs);
  first_rr.dtree_v2 = tomatch ;
  first_rr.tree_v2 = nomatch ;
}
-}




{- Version 3.

   A fix to version 1.  Now pass in the tree to match against and the
   tree to return if no match is made.  Both will presumably be
   decorated.

   Thus, we need to 'new' the results of the functions that apply
   rewrite rules in this version.
-}
{-
nonterminal RewriteRule_v3<a> ;

synthesized attribute match_v3 :: Boolean occurs on RewriteRule_v3<a> ;
synthesized attribute rewrite_v3<a> :: a occurs on RewriteRule_v3<a> ;

inherited attribute tree_to_match_v3<a> :: a occurs on RewriteRule_v3<a> ;
--inherited attribute tree_if_no_match_v3<a> :: a occurs on RewriteRule_v3<a> ;

function applyRewriteRule_v3
t ::= rr::RewriteRule_v3<t> tomatch::t nomatch::t
{ return if rr.match_v3 then rr.rewrite_v3 else nomatch ;
  rr.tree_to_match_v3 = tomatch ;
}
-}
