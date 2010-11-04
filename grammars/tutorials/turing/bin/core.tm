machine LSeek0 {
	start s0 {
	  0 -> s1 : move left,
	  1 -> s1 : move left
	}

	s1 {
	  1 -> s1 : move left,
	  0 -> end : nothing
	}
}

tape 0,1,1,1,[0];
run machine LSeek0;


machine RSeek0 {
	start s0 {
	  0 -> s1 : move right,
	  1 -> s1 : move right
	}

	s1 {
	  1 -> s1 : move right,
	  0 -> end : nothing
	}
}

machine R {
	start s0 {
	  0 -> end : move right,
	  1 -> end : move right
	}
}

machine L {
	start s0 {
	  0 -> end : move left,
	  1 -> end : move left
	}
}

machine T0 {
	start s0 {
	  0 -> end : print 0,
	  1 -> end : print 0
	}
}

machine T1 {
	start s0 {
	  0 -> end : print 1,
	  1 -> end : print 1
	}
}
