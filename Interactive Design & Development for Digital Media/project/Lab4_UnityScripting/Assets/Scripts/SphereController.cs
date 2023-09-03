using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SphereController : MonoBehaviour
{

    public float speed; // control the speed of translation

    // a public reference/variable to the ‘Cube’ object in the scene
    // being pubic allows it visible in the Unity editor
    // so we could associate it with the ‘Cube’ object by drag & drop
    public GameObject myCube;

    void Start()
    {
        speed = 5; // it’s always good to initialize the variable
    }

    void Update()
    {
        ControlByArrowKeys(speed);
    }

    void ControlByArrowKeys(float speed)
    {
        float xMove = Input.GetAxis("Horizontal") * speed;
        float zMove = Input.GetAxis("Vertical") * speed;
        transform.Translate(xMove, 0.0f, zMove);

        // to transform the ‘Cube’ in the same pace/speed
        // as ‘this’ object, i.e. Sphere
        // Note that the script is attached to the ‘Sphere’. 
        myCube.transform.Translate(xMove, 0.0f, zMove);
    }
}

