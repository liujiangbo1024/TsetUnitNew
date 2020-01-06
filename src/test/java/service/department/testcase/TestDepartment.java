package service.department.testcase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.department.api.Department;

import java.util.ArrayList;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class TestDepartment {
   static Department department=new Department();

    @BeforeAll
    public static void beforeAll(){
        //先将parentDeptID下的部门全部删除
         ArrayList<Integer> ids=department.list(department.parentDeptID).then()
                  .extract().body().path("department.findAll{d->d.parentid =="+department.parentDeptID+"}.id");
          System.out.println("ids="+ids);
          ids.forEach(id -> department.delete(id));
    }
    @Test
    public void list(){
     department.list(department.parentDeptID).then().body("errmsg",equalTo("ok"));
    }
    @Test
    public void create(){
       /*ArrayList<Integer> ids=department.list(department.parentDeptID).then()*/
       /*         .extract().body().path("department.findAll{d->d.parentid =="+department.parentDeptID+"}.id");*/
       /* System.out.println("ids="+ids);*/
       /* ids.forEach(id -> department.delete(id));*/
        String  name="部门2";
        department.create(name,department.parentDeptID).then().body("errmsg",equalTo("created"));
        department.list(department.parentDeptID).then().body("department.findAll{d->d.name=='"+name+"'}.id",hasSize(1));  // 这个写法要注意

       // department.list(department.parentDeptID).then().body("",equalTo(""));
    }
    @Test void delete(){
         int  deptid=  department.create("部门3",department.parentDeptID).then().body("errmsg",equalTo("created")).extract().body().path("id");
         System.out.println("deptid");
         department.delete(deptid).then().body("errmsg",equalTo("deleted"));
    }
}
