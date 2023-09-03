using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CubeUpdateTesting : MonoBehaviour
{

    //private float speed = 0.2f;
    public float speed = 0.02f;

    private Rigidbody rb;

    void Start()
    {
        rb = GetComponent<Rigidbody>();
    }
    void Update()
    {
        ControlByArrowKeys(speed);

    }

    void FixedUpdate()
    {
        //ControlByArrowKeys(speed);
    }
    void LateUpdate()
    {
    }
    void OnCollisionEnter(Collision coll)
    {
        Debug.Log("CollisionEnter");
    }
    void OnCollisionStay(Collision coll)
    {
        Debug.Log("CollisionStay");
    }
    void OnCollisionExit(Collision coll)
    {
        Debug.Log("CollisionExit");
    }
    void OnTriggerEnter(Collider other)
    {
        Debug.Log("OnTriggerEnter");
    }
    void OnTriggerStay(Collider other)
    {
        Debug.Log("OnTriggerStay");
    }
    void OnTriggerExit(Collider other)
    {
        Debug.Log("OnTriggerExit");
    }
    void ControlByArrowKeys(float speed)
    {
        float xMove = Input.GetAxis("Horizontal") * speed;
        float zMove = Input.GetAxis("Vertical") * speed;

        // use this method only for an object with Rigidbody component attached
        // and it is not ‘Kinematic’
        rb.AddForce(xMove, 0.0f, zMove);
    }
}