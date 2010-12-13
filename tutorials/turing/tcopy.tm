//load the core system
load "core.tm";

//define the tape
tape 1, 1, 1, 1, 1, 1, 1, 1, [0];

machine TCopy {
	//define the start state
	start q0 : LSeek0 {0 -> q1}
	q1 : R {0 -> q2, 1 -> q3}

	q2 : RSeek0 {0 -> q10}

	q3 : T0 {0 -> q4}
	q4 : RSeek0 {0 -> q5}
	q5 : RSeek0 {0 -> q6}
	q6 : T1 {1 -> q7}
	q7 : LSeek0 {0 -> q8}
	q8 : LSeek0 {0 -> q9}
	q9 : T1 {1 -> q1}
}

run machine TCopy;

