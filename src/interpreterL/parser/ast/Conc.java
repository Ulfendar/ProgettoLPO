package interpreterL.parser.ast;

import interpreterL.visitors.Visitor;

public class Conc extends BinaryOp{

    public Conc(Exp left, Exp right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitConc(left, right);
    }

}

