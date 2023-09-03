using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PongBallController : MonoBehaviour
{
    public float m_speed;
    private Rigidbody2D rb;


    // Start is called before the first frame update
    void Start()
    {
        rb = GetComponent<Rigidbody2D>();
        rb.velocity = Vector2.up * m_speed;
        // Debug.Log(rb.velocity);
    }


    // Update is called once per frame
    void Update()
    {
        //transform.Translate(Vector3.up * m_speed * Time.deltaTime);
    }

    void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.CompareTag("Block"))
        {
            // Vector2 newDirection = Vector2.Reflect(rb.velocity, collision.contacts[0].normal);
            // rb.velocity = newDirection;
            // Debug.Log(newDirection);
            Destroy(collision.gameObject);
        }
        if(collision.gameObject.CompareTag("Void"))
        {
            Destroy(gameObject);
        }
        // else if (collision.gameObject.CompareTag("Wall"))
        // {
        //     Vector2 newDirection = Vector2.Reflect(rb.velocity, collision.contacts[0].normal);
        //     rb.velocity = newDirection;
        // }
    }


}
