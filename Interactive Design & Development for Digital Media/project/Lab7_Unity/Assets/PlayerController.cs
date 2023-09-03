using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class NewBehaviourScript : MonoBehaviour
{
    Animator anim;
    public RuntimeAnimatorController runController;
    public RuntimeAnimatorController idleController;
    public RuntimeAnimatorController jumpUpController;

    void Start()
    {
        anim = GetComponent<Animator>();
    }

    void Update()
    {
        // if (Input.GetKey(KeyCode.UpArrow) == true)
        // {
        //     anim.runtimeAnimatorController = runController;
        // }
        // else
        // {
        //     anim.runtimeAnimatorController = idleController;
        // }
        if (Input.GetKey(KeyCode.W))
        {
            anim.runtimeAnimatorController = runController;
        }
        else if (Input.GetKey(KeyCode.Space))
        {
            anim.runtimeAnimatorController = jumpUpController;
            // if (Input.GetKey(KeyCode.A))
            // {
            //     anim.SetBool("left", true);
            // }
            // else if (Input.GetKey(KeyCode.D))
            // {
            //     anim.SetBool("left", false);
            // }

        }
        else
        {
            anim.runtimeAnimatorController = idleController;
        }
    }

}
