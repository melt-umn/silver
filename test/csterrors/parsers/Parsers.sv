grammar parsers;

imports silver:testing;
imports lib:extcore;
imports disamb as dis;
imports prodMod as pro;
imports startMissing as sta;
imports mda as mda;
imports silver:langutil;
imports silver:langutil:pp;
imports core:monad;

function main
IOVal<Integer> ::= args::[String] ioIn::IO
{
    local result::IOMonad<Integer> =  do (bindIO, returnIO) {
       return if null(dis:extendedParser("","").parseErrors) ||
         null(pro:extendedParser("","").parseErrors) ||
         null(sta:extendedParser("","").parseErrors) ||
         null(mda:extendedParser("","").parseErrors) then 1
       else 0;
    };
    return evalIO(result, ioIn);
}