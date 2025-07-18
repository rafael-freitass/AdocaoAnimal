module br.edu.ifpr {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.desktop;
    requires jdk.jfr;
    requires org.slf4j;

    opens br.edu.ifpr.model to jakarta.persistence, org.hibernate.orm.core;

    opens br.edu.ifpr.controller to javafx.fxml;
    exports br.edu.ifpr;
}