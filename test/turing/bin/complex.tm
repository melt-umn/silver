//load the core system
load "core.tm";
load "tcopy.tm";

//define the tape
tape 1, 1, 1, 1, 1, 1, 1, 1, [0];

machine Complex {
  start q0 : TCopy {0 -> q1}
  q1 : LSeek0 {0 -> q2}
  q2 : T1 {1 -> q3}
  q3 : RSeek0 {0 -> q4}

// Either way works
  q4 : TCopy {}
//q4 : TCopy {0 -> end, 1 -> end}
}


run machine Complex;
