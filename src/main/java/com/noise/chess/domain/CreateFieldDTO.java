package com.noise.chess.domain;

public class CreateFieldDTO {
    private final boolean playWhites;
    private final String nickName;
    
    public CreateFieldDTO() {
        this.playWhites = false;
        this.nickName = null;
    }

    public CreateFieldDTO(boolean playWhites, String nickName) {
        this.playWhites = playWhites;
        this.nickName = nickName;
    }

    public boolean isPlayWhites() {
        return playWhites;
    }

    public String getNickName() {
        return nickName;
    }
}
