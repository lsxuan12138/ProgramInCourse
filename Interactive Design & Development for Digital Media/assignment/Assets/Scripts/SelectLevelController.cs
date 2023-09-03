using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class SelectLevelController : MonoBehaviour
{
    public void OnLevel1Click(){
        PlayerPrefs.SetInt("endless", 0);
        SceneManager.LoadScene("Game");
    }
    public void OnLevel2Click(){
        PlayerPrefs.SetInt("endless", 0);
        SceneManager.LoadScene("Game");
    }
    public void OnLevel3Click(){
        PlayerPrefs.SetInt("endless", 1);
        SceneManager.LoadScene("Game");
    }

    public void OnEndlessClick(){
        PlayerPrefs.SetInt("endless", 1);
        SceneManager.LoadScene("Endless");
    }
    public void OnHeroClick()
    {
        PlayerPrefs.SetInt("endless", 1);
        SceneManager.LoadScene("HeroGame");
    }
}
