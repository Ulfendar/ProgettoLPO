package interpreterL.parser.ast;

import interpreterL.visitors.Visitor;

public class In extends BinaryOp {

    public In(Exp right, Exp left){
        super(right, left);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitIn(left, right);
    }

}
