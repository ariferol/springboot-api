//package tr.com.common.security.model;
//
//import jakarta.persistence.*;
//import tr.com.common.base.BaseEntityAudit;
//
//@Entity
//@Table(name = "roles")
//public class Role extends BaseEntityAudit<Integer> {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column(length = 20)
//    private String name;
//
//    public Role() {
//
//    }
//
//    public Role(String name) {
//        this.name = name;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}
