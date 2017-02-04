package com.agna.realmvp.realmvpsample.ui.base.screen;


import com.agna.realmvp.realmvpsample.ui.base.screen.model.ScreenModel;

public interface RenderableView<M extends ScreenModel>{
    void render(M screenModel);
}
