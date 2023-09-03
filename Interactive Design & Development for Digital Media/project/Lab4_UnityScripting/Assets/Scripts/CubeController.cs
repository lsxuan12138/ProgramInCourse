using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CubeController : MonoBehaviour
{
    private float speed;

    private Rigidbody rb;

    // Start is called before the first frame update
    void Start()
    {
        Debug.Log("Start method");
        speed = 0.5f;
        Debug.Log($"speed: {speed}");
        rb = GetComponent<Rigidbody>();
    }

    // Update is called once per frame
    void Update()
    {
        //transform.Translate(1, 0, 0);
        //transform.Translate(1 * Time.deltaTime, 0, 0);
        //transform.Rotate(45, 45, 45);
        //transform.Rotate(45 * Time.deltaTime, 45 * Time.deltaTime, 45 * Time.deltaTime);
        //ControlByArrowKeys(speed);
    }
    void FixedUpdate()
    {
        ControlByArrowKeys(speed);
        if (Input.GetKeyDown(KeyCode.Plus) || Input.GetKeyDown(KeyCode.Equals))
        {
            Debug.Log("speed increase");

            if (speed < 1.0f)
            {
                speed += 0.1f;
            }
            else if (speed < 10.0f)
            {
                speed += 1.0f;
            }
            Debug.Log($"speed: {speed}");
        }

        if (Input.GetKeyDown(KeyCode.Minus))
        {
            Debug.Log("speed reduce");
            if (speed <= 0.0f)
            {
                speed = 0.0f;
            }
            else if (speed <= 1.0f)
            {
                speed -= 0.1f;
            }
            else if (speed <= 10.0f)
            {
                speed -= 1.0f;
            }
            Debug.Log($"speed: {speed}");
        }
    }
    void ControlByArrowKeys(float speed)
    {
        // float xMove = Input.GetAxis("Horizontal"); // move along the x direction
        // float zMove = Input.GetAxis("Vertical"); // move along the z direction
        // transform.Translate(xMove, 0.0f, zMove);

        float xMove = Input.GetAxis("Horizontal") * speed;
        float zMove = Input.GetAxis("Vertical") * speed;
        rb.AddForce(xMove, 0.0f, zMove);
        if (xMove != 0.0f || zMove != 0.0f)
        {
            Debug.Log($"xMove = {xMove}, zMove = {zMove}");
        }
        if (Input.GetMouseButtonDown(0))
        {
            Debug.Log("up");
            rb.AddForce(0.0f, 10.0f * speed, 0.0f);
        }
        if (Input.GetMouseButtonDown(1))
        {
            Debug.Log("down");
            rb.AddForce(0.0f, -10.0f * speed, 0.0f);
        }

    }

}
