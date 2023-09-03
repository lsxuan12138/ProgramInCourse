using UnityEngine;

/*
 * On screen HUD to display current health.
 */
public class PlayerHud : MonoBehaviour
{
  private PlayerController playerController;
  private Texture2D halfHeart;
  private Texture2D heart;

  /*
   * Load and store the heart images and cache the PlayerController
   * component for later.
   */
  private void Start()
  {
    playerController = GetComponent<PlayerController>();
    heart = Resources.Load<Texture2D>("heart");
    halfHeart = Resources.Load<Texture2D>("halfHeart");

  }

  /*
   * Using the current health from the PlayerController, display the
   * correct number of hearts and half hearts.
   */
  private void OnGUI()
  {
    
    int health = playerController.GetHealth();
    //Debug.Log(health);
    int heartNum = health / 2;
    //Debug.Log(heartNum);
    int halfHeartNum = health % 2;
    //Debug.Log(halfHeartNum);
    int i = 0;
    for (; i < heartNum; i++)
    {
      GUI.DrawTexture(new Rect(10 + i * 50, 10, 50, 50), heart);
    }
    if (halfHeartNum != 0) GUI.DrawTexture(new Rect(10 + i * 50, 10, 50, 50), halfHeart);

    Color textColor = Color.black;
    GUIStyle labelStyle = new GUIStyle(GUI.skin.label);
    labelStyle.fontSize = 20;
    labelStyle.normal.textColor = textColor;
    GUI.Label(new Rect(10, 70, 100, 50), "Score: " + DataHolder.score, labelStyle);
  }
}
