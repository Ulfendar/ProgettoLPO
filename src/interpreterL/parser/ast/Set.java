package interpreterL.parser.ast;

import interpreterL.visitors.Visitor;

public class Set extends MoreExp implements Exp{


    public Set(Exp first, ExpSeq res) {
        super(first, res);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitSet(first, rest);
    }
}
