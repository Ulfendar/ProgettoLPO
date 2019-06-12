package interpreterL.visitors.evaluation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class SetValue extends PrimValue<List<Value>>  {

    public SetValue(List<Value> elem) {
        super(elem);
    }

    public List<Value> getValues() {
        return value;
    }



    public SetValue Intersection(SetValue elem) {

        SetValue set = new SetValue(new ArrayList<Value>() );
        for(Value cur : value)
            if(elem.isIn(cur))
                set.add(cur);

            return set;
    }

    public SetValue Union(SetValue elem) {

        SetValue set = new SetValue(elem.getValues());
        for(Value cur : value)
            if(!set.isIn(cur))
                set.add(cur);

        return set;
    }

    public int Size(){
        return value.size();
    }


    public void add(Value val){
        if(!value.contains(val))
            value.add(val);
    }

    public boolean isIn(Value val){
        return value.contains(val);

    }



    @Override
    public final boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof PairValue))
            return false;
        SetValue op = (SetValue) obj;
        return value.equals(op.value);
    }

    @Override
    public SetValue asSet() {
        return this;
    }

    @Override
    public String toString() {
        if (value.isEmpty()){
            return "{}";
        }
        String string = "{";
        string += String.valueOf(value.get(0));

        for(int i =1; i<value.size(); ++i)
            string += ", "+ String.valueOf(value.get(i));

        string += "}";
        return string;
    }



}
