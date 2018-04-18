package org.prj.arachne.domain.member;


//RepoPosition

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class MemberMirrorSetting {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "setting_owner")
    private MemberAccount settingOwner;

    int posWeather;
    int posCalendar;
    int posTodoList;
    int posCosRecom;
    int posNews;
    int posWatch;


}
