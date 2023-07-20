import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;

class MainInterface extends JFrame implements ActionListener {

  JButton new_book, Booking_diary, checkout_pay, add_room, logout;

  JLabel l1, l2, l3, l4;

  MainInterface() {

    this.setSize(500, 500);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setComponents();
  }

  public void setComponents() { // function for create GUI

    setLayout(null);

    new_book = new JButton("New Booking");
    new_book.setBounds(50, 90, 200, 50);
    new_book.setFont(new Font("Serif", Font.BOLD, 20));
    add(new_book);

    Booking_diary = new JButton("Booking Diary");
    Booking_diary.setBounds(50, 160, 200, 50);
    Booking_diary.setFont(new Font("Serif", Font.BOLD, 20));
    add(Booking_diary);

    checkout_pay = new JButton("Checkout");
    checkout_pay.setBounds(50, 230, 200, 50);
    checkout_pay.setFont(new Font("Serif", Font.BOLD, 20));
    add(checkout_pay);

    logout = new JButton("LogOut");
    logout.setBounds(100, 330, 150, 50);
    logout.setFont(new Font("Serif", Font.BOLD, 15));
    add(logout);

    l1 = new JLabel("Welcome ");
    l1.setBounds(20, 20, 300, 50);
    l1.setFont(new Font("Serif", Font.BOLD, 40));
    add(l1);

    this.setVisible(true);

    new_book.addActionListener(this);

    Booking_diary.addActionListener(this);

    checkout_pay.addActionListener(this);
    logout.addActionListener(this);

  }

  public void actionPerformed(ActionEvent ae) {

    String str = ae.getActionCommand();

    if (str.equals("New Booking")) {
      new NewBooking();

    } else if (str.equals("Booking Diary")) {
      new BookingDiary();

    }

    else if (str.equals("Checkout")) {
      new Checkout();

    } else if (str.equals("LogOut")) {
      new App2();
      dispose();

    }

  }

}
