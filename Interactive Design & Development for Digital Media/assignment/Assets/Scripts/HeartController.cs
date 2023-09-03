using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class HeartController : MonoBehaviour
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
        PlayerController playerController =
          other.gameObject.GetComponent<PlayerController>();

        playerController.AddHealth();
        // Make this object disappear
        GameObject.Destroy(gameObject);
    }
}
