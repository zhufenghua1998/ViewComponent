package entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class User_Complex {

    private List<String> list;
    private Set<String> set;
    private Map<String,Integer> map;

    public User_Complex(){

    }

    public User_Complex(List<String> list, Set<String> set, Map<String, Integer> map) {
        this.list = list;
        this.set = set;
        this.map = map;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "User_Complex{" +
                "list=" + list +
                ", set=" + set +
                ", map=" + map +
                '}';
    }
}
