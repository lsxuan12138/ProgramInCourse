using UnityEngine;

/*
 * Attached to the section so that everything will scroll sideways.
 * The player does not move in this game, the environment does.
 */
public class SectionScroller : MonoBehaviour
{
  public GameObject obstaclePrefab;
  public GameObject starPrefab;
  public GameObject heartPrefab;
  float preX;

  float speed;
  static float LowY = -3.2f;
  static float HighY = 2.5f;
  bool canGenerate;
  private void Start()
  {
    speed = 1.0f;
    preX = 10.0f;
    canGenerate = true;
  }
  /*
   * Use the Transform component attached to the section game object and
   * translate it based on delta time.
   */
  private void Update()
  {
    PlayerPrefs.SetInt("endless", 1);

    if (PlayerPrefs.GetInt("endless") == 1)
    {
      // 获取当前对象的位置
      Vector3 currentPosition = transform.position;
      float positionX = Mathf.Abs(currentPosition.x);
      if (positionX >= preX && canGenerate)
      {
        preX = generate(positionX);
        if (preX >= 190f * speed - 30f) canGenerate = false;
      }
      transform.Translate(new Vector2(-10, 0) * Time.deltaTime * speed);
      //循环地图
      if (currentPosition.x < -(190f * speed - 10f))
      {
        canGenerate = true;
        //设置速度（地图缩放因子）
        speed += 0.05f;
        //缩放地图
        transform.localScale = new Vector3(speed, 1, 1);
        //Debug.Log("remake");
        // 更新section的位置
        float newXPosition = 10f * speed - 10f;
        currentPosition.x = newXPosition;
        transform.position = currentPosition;
        preX = 10f * speed - 20f;
        //每次奖励score
        int score = DataHolder.score + 100;
        DataHolder.score = score;
      }
    }
    else
    {
      transform.Translate(new Vector2(-10, 0) * Time.deltaTime);
    }

  }
  private float generate(float positionX)
  {
    int i = Random.Range(0, 30);
    GameObject childInstance;
    //生成空中的障碍
    if (i <= 4)
    {
      // 创建子对象实例
      childInstance = Instantiate(obstaclePrefab, transform.position + new Vector3(positionX + 10f, HighY, 0), transform.rotation);
      // 将子对象设置为当前对象的子对象
      childInstance.transform.SetParent(transform);
      //Debug.Log(positionX + 5f * speed);
      return (positionX + 5f * speed);
    }
    //生成地上的障碍
    else if (i <= 20)
    {
      childInstance = Instantiate(obstaclePrefab, transform.position + new Vector3(positionX + 10f, LowY, 0), transform.rotation);        // 将子对象设置为当前对象的子对象
      childInstance.transform.SetParent(transform);
      //Debug.Log(positionX + 13f * speed);
      return (positionX + 13f * speed);
    }
    //生成星星，增加分数
    else if (i <= 27)
    {
      childInstance = Instantiate(starPrefab, transform.position + new Vector3(positionX + 10f, Random.Range(LowY, HighY), 0), transform.rotation);        // 将子对象设置为当前对象的子对象
      childInstance.transform.SetParent(transform);
      //Debug.Log(positionX + 3f * speed);
      return (positionX + 3f * speed);
    }
    //生成心形，增加生命
    else
    {
      childInstance = Instantiate(heartPrefab, transform.position + new Vector3(positionX + 10f, Random.Range(LowY, HighY), 0), transform.rotation);        // 将子对象设置为当前对象的子对象
      childInstance.transform.SetParent(transform);
      //Debug.Log(positionX + 3f * speed);
      return (positionX + 3f * speed);
    }


  }
}
