using UnityEngine;

/*
 * Provide the obstacles with a way of damaging the player.
 */
public class ObstacleCollider : MonoBehaviour
{
  /*
   * A trigger callback to detect when the player's collider has
   * entered the obstacle's. Then simply obtain the PlayerController
   * reference can apply damage. Then remove the obstacle for feedback.
   */
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
      
    playerController.musicPlay();
    // Register damage with player
    playerController.Damage();

    // Make this object disappear
    GameObject.Destroy(gameObject);
  }
}
