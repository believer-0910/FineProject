package service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static utils.Const.*;

public class BotService {

    public static InlineKeyboardMarkup mainInlineButtons(){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineKeyBtn = new ArrayList<>();
        List<InlineKeyboardButton> buttons = new ArrayList<>();

        InlineKeyboardButton button = new InlineKeyboardButton();

        button.setText(SEARCH_USER);
        button.setCallbackData(SEARCH_USER);
        buttons.add(button);

        button = new InlineKeyboardButton();
        button.setText(SEARCH_CAR);
        button.setCallbackData(SEARCH_CAR);
        buttons.add(button);

        button = new InlineKeyboardButton();
        button.setText(SEARCH_FINE);
        button.setCallbackData(SEARCH_FINE);
        buttons.add(button);
        inlineKeyBtn.add(buttons);

        buttons = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(BACK);
        button.setCallbackData(BACK);
        buttons.add(button);
        inlineKeyBtn.add(buttons);

        inlineKeyboardMarkup.setKeyboard(inlineKeyBtn);
        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup searchUserInlineButtons(){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineKeyBtn = new ArrayList<>();
        List<InlineKeyboardButton> buttons = new ArrayList<>();

        InlineKeyboardButton button = new InlineKeyboardButton();

        button.setText(SEARCH_USER_PASSPORT);
        button.setCallbackData(SEARCH_USER_PASSPORT);
        buttons.add(button);

//        inlineKeyBtn.add(buttons);
        button = new InlineKeyboardButton();

        button.setText(SEARCH_USER_ID);
        button.setCallbackData(SEARCH_USER_ID);
        buttons.add(button);
        inlineKeyBtn.add(buttons);

        buttons = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(BACK);
        button.setCallbackData(BACK);
        buttons.add(button);
        inlineKeyBtn.add(buttons);


        inlineKeyboardMarkup.setKeyboard(inlineKeyBtn);
        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup searchFineInlineButtons(){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineKeyBtn = new ArrayList<>();
        List<InlineKeyboardButton> buttons = new ArrayList<>();

        InlineKeyboardButton button = new InlineKeyboardButton();

        button.setText(SEARCH_FINE_BY_CAR_NUMBER);
        button.setCallbackData(SEARCH_FINE_BY_CAR_NUMBER);
        buttons.add(button);

//        inlineKeyBtn.add(buttons);
        button = new InlineKeyboardButton();

        button.setText(SEARCH_FINE_BY_CAR_NUMBER_AND_MONTH);
        button.setCallbackData(SEARCH_FINE_BY_CAR_NUMBER_AND_MONTH);
        buttons.add(button);
        inlineKeyBtn.add(buttons);


        buttons = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(BACK);
        button.setCallbackData(BACK);
        buttons.add(button);
        inlineKeyBtn.add(buttons);

        inlineKeyboardMarkup.setKeyboard(inlineKeyBtn);
        return inlineKeyboardMarkup;
    }
}
