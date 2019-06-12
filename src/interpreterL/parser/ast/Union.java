package interpreterL.parser.ast;

import interpreterL.visitors.Visitor;

public class Union extends BinaryOp{


    public Union(Exp set1, Exp set2){
        super(set1, set2);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitUnion(left, right);
    }


}
