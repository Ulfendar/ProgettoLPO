package interpreterL.visitors.evaluation;

import java.util.Set;

public interface Value {
	/* default conversion methods */
	default int asInt() {
		throw new EvaluatorException("Expecting an integer");
	}

	default String asString(){
		throw new EvaluatorException("Expecting a String");
	}

	default boolean asBool() {
		throw new EvaluatorException("Expecting a boolean");
	}

	default PairValue asPair() {
		throw new EvaluatorException("Expecting a pair");
	}

	default SetValue asSet() {
		throw new EvaluatorException("Expecting a set");
	}



}
