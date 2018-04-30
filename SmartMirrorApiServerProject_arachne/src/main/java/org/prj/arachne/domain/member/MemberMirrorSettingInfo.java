package org.prj.arachne.domain.member;


//RepoPosition

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.prj.arachne.presentation.dto.MirrorSettingDTO;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MemberMirrorSettingInfo {

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "setting_owner")
    private MemberAccount settingOwner;

    private int posWeather;
    private int posCalendar;
    private int posTodoList;
    private int posCosRecom;
    private int posNews;
    private int posWatch;


    public void modifyMirrorSetting(MirrorSettingDTO dto){

        this.setPosCalendar(dto.getPosWeather());
        this.setPosCalendar(dto.getPosCalendar());
        this.setPosTodoList(dto.getPosTodoList());
        this.setPosCosRecom(dto.getPosCosRecom());
        this.setPosNews(dto.getPosNews());
        this.setPosWatch(dto.getPosWatch());

    }


}
