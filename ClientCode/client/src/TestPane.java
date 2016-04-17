import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import teleger.SafeUser;
import teleger.ServerInterface;

    public class TestPane extends JPanel {

        private JPanel mainList;
        private  JLabel imageBar;
		private JLabel nameBar;
		private JPanel contenedor;
		
        public TestPane(ArrayList<SafeUser> friends, JLabel imageBar, JLabel nameBar, JPanel contenedor){
        	this.imageBar = imageBar;
        	this.nameBar = nameBar;
        	this.contenedor = contenedor;
        	setLayout(new BorderLayout());
//
        	mainList = new JPanel(new GridBagLayout());
//
            add(new JScrollPane(mainList));
            if(friends!=null)
            for(int i=0; i<friends.size();i++){
            	GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.weightx = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;

                System.out.println("contenedooooooooor"+contenedor.getComponentCount());
                mainList.add(new FriendPanel(friends.get(i).image, friends.get(i).id, this.imageBar, this.nameBar, contenedor), gbc, 0);
                System.out.println("contenedooooooooor"+contenedor.getComponentCount());
                validate();
                repaint();
            }
            
            
        }
        
        public void addFriend(SafeUser friend){
        	//mainList.revalidate();
        	GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            System.out.println("contenedooooooooor"+contenedor.getComponentCount());
            mainList.add(new FriendPanel(friend.image, friend.id, this.imageBar, this.nameBar, contenedor), gbc, 0);
            System.out.println("contenedooooooooor"+contenedor.getComponentCount());
            validate();
            repaint();
        }
        
        public void addSearchFriends(ArrayList<SafeUser> friends, ServerInterface server, SafeUser user){
        	mainList.removeAll();
        	mainList.revalidate();
        	for(int i=0; i<friends.size();i++){
        		GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.weightx = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                mainList.add(new SearchFriendPanel(friends.get(i), server, user), gbc, 0);
                validate();
                repaint();
        	}
        }
        public void addFriends(ArrayList<SafeUser> friends){
        //	mainList.removeAll();
        	mainList.revalidate();
        	for(int i=0; i<friends.size();i++){
        		GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.weightx = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                System.out.println("contenedooooooooor"+contenedor.getComponentCount());
                mainList.add(new FriendPanel(friends.get(i).image, friends.get(i).id, this.imageBar, this.nameBar, contenedor), gbc, 0);
                System.out.println("contenedooooooooor"+contenedor.getComponentCount());
                validate();
                repaint();
        	}
        }
        
        public TestPane() {
            setLayout(new BorderLayout());

            mainList = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 1;
            gbc.weighty = 1;
            mainList.add(new JPanel(), gbc);

            add(new JScrollPane(mainList));

            JButton add = new JButton("Add");
            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
					JPanel panel = new JPanel();
                    panel.add(new JLabel("Hello"));
//                    panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridwidth = GridBagConstraints.REMAINDER;
                    gbc.weightx = 1;
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    mainList.add(panel, gbc, 0);

                    validate();
                    repaint();
                }
            });

            add(add, BorderLayout.SOUTH);

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 200);
        }
    }
