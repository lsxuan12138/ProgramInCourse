using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class StarController : MonoBehaviour
{
    private void OnTriggerEnter2D(Collider2D other)
    {
        if (other.gameObject.CompareTag("Damager"))
        {
            //Debug.Log("damage");
            GameObject.Destroy(gameObject);
            return;
        }
        // Obtain a reference to the Player's PlayerController
        int score = DataHolder.score + 10;
        DataHolder.score = score;
        // Make this object disappear
        GameObject.Destroy(gameObject);
    }
}
