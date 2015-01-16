package com.erayarslan.publeak.util;

import com.erayarslan.publeak.R;
import com.tuenti.buttonmenu.viewmodel.button.ButtonVM;
import com.tuenti.buttonmenu.viewmodel.button.SimpleButtonVM;
import com.tuenti.buttonmenu.viewmodel.buttonmenu.SimpleButtonMenuVM;

public class CustomButtonMenuVM extends SimpleButtonMenuVM {

    private final ButtonVM photo = new SimpleButtonVM(R.layout.photo_button, R.id.photo, null);

    public CustomButtonMenuVM() {
        super();
        addItem(photo);
    }

}
