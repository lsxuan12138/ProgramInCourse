using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ScoreLoader : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        int score = DataHolder.score;
        // 更新UI元素
        GetComponent<Text>().text = "Your score is: " + score.ToString();
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
