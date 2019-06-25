package interpreterL.parser.ast;

import interpreterL.visitors.Visitor;

public class Size extends UnaryOp{

    public Size(Exp exp) {
        super(exp);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitSize(exp);
    }
}
