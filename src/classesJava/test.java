package classesJava;

import org.apache.log4j.Logger;

public class test {
    // Инициализация логера
    private static final Logger log = Logger.getLogger(test.class);

    public void doOrder(){
        // какае-то логика
        System.out.println("Заказ оформлен!");
        // логируем инфо
        log.info("Это информационное сообщение!");
        addToCart();
    }

    private void addToCart() {
        // добавление товара в корзину
        System.out.println("Товар добавлен в корзину");
        // логируем ошибку
        log.error("Это сообщение ошибки");
    }

    public static void main(String[] args) {
        test test = new test();
        test.doOrder();
        test.addToCart();

    }
}
