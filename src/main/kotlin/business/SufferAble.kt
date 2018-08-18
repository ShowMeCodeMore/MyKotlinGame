package business

import model.View


/**
 * ClassName:SufferAble
 * Description:受攻击能力
 */
interface SufferAble:View {
    //血量属性
    var blood:Int
    /**
     * 通知受到攻击了
     */
    fun notifyAttack(attack:AttackAble):Array<View>?
}