using UnityEngine;
using UnityEngine.SceneManagement;

/*
 * Behaviour to handle keyboard input and also store the player's
 * current health.
 */
public class PlayerController : MonoBehaviour
{
  public Rigidbody2D rigidbody2d;
  public int health;
  public bool canJump;
  public AudioSource music;
  public bool canFly = false;
    /*
     * Apply initial health and also store the Rigidbody2D reference for
     * future because GetComponent<T> is relatively expensive.
     */
    private void Start()
  {
    health = 6;
        rigidbody2d = GetComponent<Rigidbody2D>();
        music = GetComponent<AudioSource>();
        DataHolder.score = 0;
    }

  /*
   * Remove one health unit from player and if health becomes 0, change
   * scene to the end game scene.
   */
  public void Damage()
  {
    health -= 1;

    if (health < 1)
    {
      SceneManager.LoadScene("EndGame");
    }
  }

  /*
   * Accessor for health variable, used by he HUD to display health.
   */
  public int GetHealth()
  {
    return health;
  }
  public void AddHealth()
  {
    health+=1;
  }


    private void Awake()
    {

    }
    /*
     * Poll keyboard for when the up arrow is pressed. If the player can jump
     * (is on the ground) then add force to the cached Rigidbody2D component.
     * Finally unset the canJump flag so the player has to wait to land before
     * another jump can be triggered.
     */
    private void Update()
  {
    /*if (Input.GetKeyDown(KeyCode.UpArrow))
    {
      if (canJump == true)
      {
        rigidbody2d.AddForce(new Vector2(0, 800));
        canJump = false;
      }
    }*/
    // 按H键让生命值变为100
        if (Input.GetKeyDown(KeyCode.H))
        {
            health = 100;
        }

        //掉落，游戏结束
        if(transform.position.y < -4)
        {
            SceneManager.LoadScene("EndGame");
        }

        if (!canFly)
        {
            if (Input.GetKeyDown(KeyCode.UpArrow))
            {
                if (canJump == true)
                {
                    rigidbody2d.AddForce(new Vector2(0, 800));
                    canJump = false;
                }
            }
        }
        // 得到飞行道具
        else if (canFly == true)
        {
            if (Input.GetKeyDown(KeyCode.UpArrow))
            {
                rigidbody2d.AddForce(new Vector2(0, 500));
            }
            if (Input.GetKeyDown(KeyCode.DownArrow))
            {
                rigidbody2d.AddForce(new Vector2(0, -500));
            }
        }

        Debug.Log(rigidbody2d.velocity);
    }

  /*
   * If the player has collided with the ground, set the canJump flag so that
   * the player can trigger another jump.
   */
  private void OnCollisionEnter2D(Collision2D other)
  {
    canJump = true;
  }
  public void musicPlay()
  {
    music.Play();
  }
    public void fly()
    {
        canFly = true;
        rigidbody2d.gravityScale = 0;
    }
}
