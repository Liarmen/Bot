package com.company;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;


public class CryptoBot extends TelegramLongPollingBot {
    private static final String Botname = "Crypto_Bot";
    private static final String Botttoken = "651975352:AAH9u4bfGXviVjb5GCtvRXeswjNHLxmwYQs";

    @Override
    public void onUpdateReceived(Update update) {
        String text = update.getMessage().getText();
        String chatid =  update.getMessage().getChatId().toString();
        CCVolume ccVolume = new CCVolume();
        CryptoCurrency cryptoCurrency = new CryptoCurrency();
        if(text.equalsIgnoreCase("Вывести курс крипти")) {

            cryptoCurrency.ddsmth(text, chatid);
        }
        else if(text.equalsIgnoreCase("/start")){
           sendMessage("Привіт! Якщо хочеш побачити інформацію про криптовалюту то напиши її скорочення(Наприклад BTC)", chatid);
        } else{
            ccVolume.ddsmth(" ", chatid);
        }


    }

    public void sendMessage(String text, String chatid) {
        SendMessage sendmessage = new SendMessage();

        sendmessage.setText(text);
        sendmessage.setChatId(chatid);
        buttons(sendmessage);
        try {
            execute(sendmessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void buttons(SendMessage sendMessage){

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Вывести курс крипти"));
        keyboard.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    @Override
    public String getBotUsername() {
        return Botname;
    }

    @Override
    public String getBotToken() {
        return Botttoken;
    }
}
