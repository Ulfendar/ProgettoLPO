package interpreterL.visitors.evaluation;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class SetValue extends PrimValue<List<Value>>  {

    public SetValue(List<Value> elem) {
        super(elem);

        Set<Value> set = new LinkedHashSet<>();
        set.addAll(value);
        value.clear();
        value.addAll(set);

    }

    public List<Value> getValues() {
        return value;
    }



    public SetValue Intersection(SetValue elem) {

        List<Value> set = new ArrayList<>();
        for(Value cur : value) {
            if (elem.isIn(cur))
                set.add(cur);
        }

        return new SetValue(new ArrayList<>(set));
    }

    public SetValue Union(SetValue elem) {

        List<Value> set = new ArrayList<Value>() {{
            addAll(value);
            addAll(elem.getValues());
        }};

        return new SetValue(new ArrayList<>(set));
    }

    public int Size(){
        return value.size();
    }


    public void add(Value val){
        if(!value.contains(val))
            value.add(val);
    }

    public void remove(Value val){
        if(value.contains(val))
            value.remove(val);
    }

    public boolean isIn(Value val){
        return value.contains(val);

    }



    @Override
    public final boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof SetValue))
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
