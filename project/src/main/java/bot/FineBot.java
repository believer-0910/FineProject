package bot;

import database.CarDatabase;
import database.FineDatabase;
import database.UserDatabase;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import service.BotService;

import java.util.HashMap;
import java.util.Locale;
import java.util.Stack;

import static utils.BaseData.*;
import static utils.Const.*;

public class FineBot extends TelegramLongPollingBot {
    HashMap<String, Stack<InlineKeyboardMarkup>> state = new HashMap<>();
    String stat = "";

    public void onUpdateReceived(Update update) {

        Message message = update.hasMessage() ? update.getMessage() : update.getCallbackQuery().getMessage();
        String chatId = message.getChatId().toString();
        String text = message.getText();


        if(update.hasMessage()){
            if(text.equals("/back")){
                Stack<InlineKeyboardMarkup> markups = state.get(chatId);
                markups.pop();
                state.put(chatId, markups);
                execute(state.get(chatId).peek(),"back", chatId);
                return;
            }
            else if(text.equals("/start")){
                state = new HashMap<>();
                InlineKeyboardMarkup markup = BotService.mainInlineButtons();
                Stack<InlineKeyboardMarkup> stack = new Stack<>();
                stack.push(markup);
                state.put(chatId, stack);
                execute(markup, "Hello", chatId);
            }
            else if(stat.equals(SEARCH_CAR)){
                String carByNumber = CarDatabase.getCarByNumber(text);
                execute(null, carByNumber, chatId);
            }
            else if(stat.equals(SEARCH_FINE_BY_CAR_NUMBER)){
                String carByNumber = FineDatabase.getFineByCarNumber(text);
                execute(null, carByNumber, chatId);
            }
            else if(stat.equals(SEARCH_FINE_BY_CAR_NUMBER_AND_MONTH)){
                String[] split = text.split("#");
                String carByNumber = FineDatabase.getFineByMonthAndCarNumber(split[1].trim(), split[0].trim());
                execute(null, carByNumber, chatId);
            }
            else if(stat.equals(SEARCH_USER_PASSPORT)){
                String carByNumber = UserDatabase.getUserByPassport(text.trim().toLowerCase());
                execute(null, carByNumber, chatId);
            }
            else if(stat.equals(SEARCH_USER_ID)){
                String carByNumber = FineDatabase.getFineByUserId(text.trim().toLowerCase());
                execute(null, carByNumber, chatId);
            }
        }
        else if(update.hasCallbackQuery()){
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String data = callbackQuery.getData();

            if(data.equals(BACK)){
                Stack<InlineKeyboardMarkup> markups = state.get(chatId);
                markups.pop();
                state.put(chatId, markups);
                editInline(chatId, callbackQuery.getMessage().getMessageId(), state.get(chatId).peek(), callbackQuery.getInlineMessageId());
                return;
            }
            else if(data.equals(SEARCH_USER)){
                InlineKeyboardMarkup markup = BotService.searchUserInlineButtons();
                Stack<InlineKeyboardMarkup> markups = state.get(chatId);
                markups.push(markup);
                state.put(chatId, markups);
                editInline(chatId, callbackQuery.getMessage().getMessageId(), markup, callbackQuery.getInlineMessageId());
            }

            else if(data.equals(SEARCH_CAR)){
//                InlineKeyboardMarkup markup = BotService.searchUserInlineButtons();
//                editInline(chatId, callbackQuery.getMessage().getMessageId(), markup);
//                Stack<InlineKeyboardMarkup> markups = state.get(chatId);
//                markups.push(markup);
//                state.put(chatId, markups);
                stat = SEARCH_CAR;
                execute(null, "Enter car number", chatId);

            }

            else if(data.equals(SEARCH_FINE)){
                InlineKeyboardMarkup markup = BotService.searchFineInlineButtons();
                editInline(chatId, callbackQuery.getMessage().getMessageId(), markup, callbackQuery.getInlineMessageId());
//                execute(markup, "search fine", chatId);
                Stack<InlineKeyboardMarkup> markups = state.get(chatId);
                markups.push(markup);
                state.put(chatId, markups);
            }
            else if(data.equals(SEARCH_FINE_BY_CAR_NUMBER)){
                stat = SEARCH_FINE_BY_CAR_NUMBER;
                execute(null, "Enter car number", chatId);
            }
            else if (data.equals(SEARCH_FINE_BY_CAR_NUMBER_AND_MONTH)){
                stat = SEARCH_FINE_BY_CAR_NUMBER_AND_MONTH;
                execute(null, "Enter car number and month carNumber#month format (example: abs1234#08)", chatId);
            }
            else if(data.equals(SEARCH_USER_PASSPORT)){
                stat = SEARCH_USER_PASSPORT;
                execute(null, "Enter user passport", chatId);
            }

            else if(data.equals(SEARCH_USER_ID)){
                stat = SEARCH_USER_ID;
                execute(null, "Enter user id", chatId);
            }

            return;

        }


    }

    public String getBotUsername() {
        return USERNAME;
    }

    public String getBotToken() {
        return TOKEN;
    }

    public void editInline (String chatId, int messageId, InlineKeyboardMarkup markup, String inlineId){
        EditMessageReplyMarkup replyMarkup = new EditMessageReplyMarkup();
        replyMarkup.setChatId(chatId);
        replyMarkup.setMessageId(messageId);
        replyMarkup.setInlineMessageId(inlineId);
        replyMarkup.setReplyMarkup(markup);
        try {
            execute(replyMarkup);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void execute(InlineKeyboardMarkup markup, String text, String chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(markup);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
