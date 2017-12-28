package pers.east.family.service;

import java.util.Map;

/**
 * @Author : east.Fu
 * @Description :
 * @Date : Created in  2017/12/19 15:42
 */
public interface FamilyService {

    public void weather();

    public void baby();

    public void cooking();

    public String buildResponseMessage(Map map);

}
