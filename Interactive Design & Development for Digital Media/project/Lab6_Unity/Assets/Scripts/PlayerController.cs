using UnityEngine;
using System.Collections;

public class PlayerController : MonoBehaviour
{

    public float m_speed = 5f;
    Transform m_transform;
    Transform pongBallSpawn;
    public GameObject m_pongBallPrefab;

    void Start()
    {
        m_transform = GetComponent<Transform>();
        pongBallSpawn = m_transform.Find("pongBallSpawn");
    }

    void Update()
    {
        // TODO move input into a different component at some point

        float deltaX = 0f;
        if (Input.GetKey(KeyCode.A))
        {
            deltaX -= 1f;
        }

        if (Input.GetKey(KeyCode.D))
        {
            deltaX += 1f;
        }

        m_transform.Translate(new Vector3(deltaX, 0f, 0f) * Time.deltaTime * m_speed);
        // 检测'Space'键的按下事件
        if (Input.GetKeyDown(KeyCode.Space))
        {
            // 在pongBallSpawn对象的位置上以无旋转的方式实例化一个PongBall对象
            Instantiate(m_pongBallPrefab, pongBallSpawn.position, Quaternion.identity);
        }
    }
}
