package pl.rafalsionkowski.views.qrcode;

import com.google.zxing.WriterException;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;
import pl.rafalsionkowski.qrcode.QrCodeService;

import java.io.IOException;

@PageTitle("QR Code")
@Route(value = "main-page")
@RouteAlias(value = "")
public class QRCodeView extends VerticalLayout {


    public QRCodeView(@Autowired QrCodeService service) {


        add(new H2("Welcome! Generate your QR Code"));


        TextField textField = new TextField("Your QR string");
        IntegerField heightField = new IntegerField("Height (px)");
        IntegerField widthField = new IntegerField("Width (px)");

        textField.addThemeName("bordered");

        heightField.addThemeName("bordered");
        widthField.addThemeName("bordered");
        Image qrCodeImage = new Image();

        Button button = new Button("Generate QR Code",
                e -> {
                    try {
                        service.createQR(textField.getValue(), heightField.getValue(), widthField.getValue());

                        getUI().ifPresent(ui -> ui.navigate("qr-code-generated"));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (WriterException writerException) {
                        writerException.printStackTrace();
                    }
                });


        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.addClickShortcut(Key.ENTER);
        addClassName("centered-content");
        add(textField, button, heightField, widthField);

    }

    }

