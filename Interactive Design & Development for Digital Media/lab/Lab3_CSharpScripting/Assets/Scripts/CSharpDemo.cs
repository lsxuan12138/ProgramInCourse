using System.Collections;
using System.Collections.Generic;
using UnityEngine;

class GameObject2D
{
    string name;

    public void setName(string name)
    {
        this.name = name;
    }
    public string getName()
    {
        return name;
    }
}
class Rectange : GameObject2D
{
    int  width, height;

    public Rectange(string name, int width, int height)
    {
        this.setName(name);
        this.width = width;
        this.height = height;
    }
    public override string ToString()
    {
        return $"Rectangle {this.getName()}: width = {width}, height = {height}";
    }
}
public class CSharpDemo : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        //value_DataType();
        //reference_DataType();
        //createGameObject2D();
        createRectangle();
    }

    // Update is called once per frame
    void Update()
    {

    }
    void value_DataType()
    {
        // ‘transform.position’ gives the position of the sphere
        Debug.Log("x = " + transform.position.x + "  y = "
            + transform.position.y + "  z = " + transform.position.z);

        Vector3 v1 = transform.position;
        v1.x += 1f; v1.y += 1f; v1.z = +1f;

        Debug.Log("x = " + transform.position.x + "  y = "
            + transform.position.y + "  z = " + transform.position.z);

    }
    void reference_DataType()
    {
        // ‘transform.position’ gives the position of the sphere
        Debug.Log("x = " + transform.position.x + "  y = "
            + transform.position.y + "  z = " + transform.position.z);

        Transform trans = transform;

        // note that, here we change the ‘trans.position’, not ‘transform.position’
        trans.position = trans.position + new Vector3(1f, 1f, 1f);

        Debug.Log("x = " + transform.position.x + "  y = "
            + transform.position.y + "  z = " + transform.position.z);

    }

    void createGameObject2D()
    {

        // create 3 instances of the class ‘GameObject2D’
        // and their names are Peter, David and John
        GameObject2D peter = new GameObject2D();
        peter.setName("Peter");
        GameObject2D david = new GameObject2D();
        david.setName("David");
        GameObject2D john = new GameObject2D();
        john.setName("John");
        // define a List (C# data structure, as introduced in the lecture)
        // which holds 3 instances
        // note that, if ‘List’ is not recognized by Visual Studio, you need to 
        // include another namespace, i.e. ‘using …’.
        List<GameObject2D> list = new List<GameObject2D> { peter, david, john };
        // using the loop structure, foreach, to print names of the 3 instances
        // created earlier
        foreach (GameObject2D obj in list)
        {
            Debug.Log("name: "+obj.getName());
        }
    }
    void createRectangle()
    {
        Rectange peter = new Rectange("Peter", 1, 1);
        Rectange david = new Rectange("David", 2, 2);
        Rectange john = new Rectange("John", 3, 3);
        ArrayList list = new ArrayList();
        list.Add(peter);
        list.Add(david);
        list.Add(john);
        foreach (Rectange obj in list)
        {
            Debug.Log(obj.ToString());
        }
    }

}
