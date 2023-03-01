package com.example.fastcampusmysql.domain.memeber;

import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.util.MemberFixtureFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberTest {

    @DisplayName("Can Change NickName")
    @Test
    public void testChangeName() {
        var member = MemberFixtureFactory.create();
        var expecte = "QWER";

        member.changeNickName(expecte);

        Assertions.assertEquals(expecte, member.getNickname());

    }

    @DisplayName("Change Nick Name less then 10 Character")
    @Test
    public void testNickNameMaxLength() {
        var member = MemberFixtureFactory.create();
        var overMaxLengthName = "qwertyuiopasdf";

        Assertions.assertThrows(IllegalArgumentException.class, () -> member.changeNickName(overMaxLengthName));

//        member.changeNickName(overMaxLengthName);
//        Assertions.assertEquals(overMaxLengthName, member.getNickname());

    }

}
