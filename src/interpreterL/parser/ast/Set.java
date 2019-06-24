package interpreterL.parser.ast;

import interpreterL.visitors.Visitor;

public class Set implements Exp{

    ExpSeq elem;
    public Set(ExpSeq elem) {
       this.elem = elem;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitSet(elem);
    }
}
