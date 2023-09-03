using UnityEngine;
using UnityEngine.SceneManagement;

/*
 * Behaviour to handle keyboard input and also store the player's
 * current health.
 */
public class test : PlayerController
{
    //private AudioSource music;
    //private  new Rigidbody2D rig;
    Animator anim;
    public RuntimeAnimatorController walkController;
    public RuntimeAnimatorController flyController;

    /*
     * Apply initial health and also store the Rigidbody2D reference for
     * future because GetComponent<T> is relatively expensive.
     */
     private void Start()
     {
         health = 6;
         rigidbody2d = GetComponent<Rigidbody2D>();
         //music = GetComponent<AudioSource>();
         DataHolder.score = 0;
         anim = GetComponent<Animator>();
     }


    /*private void Start()
    {
        anim = GetComponent<Animator>();
    }*/


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
        if (transform.position.y < -4)
        {
            SceneManager.LoadScene("EndGame");
        }

            if (Input.GetKeyDown(KeyCode.UpArrow))
            {
                //if (canJump == true)
                {
                   rigidbody2d.AddForce(new Vector2(0, 800));
                    //canJump = false;
                }
            }
 
    }

    /*
     * If the player has collided with the ground, set the canJump flag so that
     * the player can trigger another jump.
     */
    private void OnCollisionEnter2D(Collision2D other)
    {
        //canJump = true;
        if(other.gameObject.CompareTag("Ground"))
        {
            anim.runtimeAnimatorController = walkController;
        }
    }
    private void OnCollisionExit2D(Collision2D collision)
    {
        if (collision.gameObject.CompareTag("Ground"))
        {
            anim.runtimeAnimatorController = flyController;
        }
    }
    
}
