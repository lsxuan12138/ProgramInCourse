using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class WingController : MonoBehaviour
{
    // Start is called before the first frame update
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
            playerController.fly();
        // Make this object disappear
        GameObject.Destroy(gameObject);
    }
}
