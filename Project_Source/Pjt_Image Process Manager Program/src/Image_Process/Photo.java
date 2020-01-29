package Image_Process;

import sun.java2d.pipe.DrawImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

    /*
        작성일 : 0128
     */

public class Photo extends JFrame {
    static int[][] inImage = new int[512][512];
    static int[][] outImage = new int[512][512];
    /*
        loadimage()으로 이미지 배열을 선언하고 사진 파일을 배열에 불러오기
     */

    Container contentPane;

    public static void main(String[] args) throws Exception {
        loadImage();
        new Photo();
    }

    static public void loadImage() throws Exception {
        int i, k;
        /*
            파일 핸들링과 파일 스트링 핸들링을 하는 기능 추가
         */
        File inFp;
        FileInputStream inFs;
        inFp = new File("C:\\Temp\\images\\lotus512.raw");

        // 읽어올 파일 스트림
        inFs = new FileInputStream(inFp.getPath());

        // 파일을 메모리로 바꾸기
        for (i = 0; i < 512; i++) {
            for (k = 0; k < 512; k++) {
                inImage[i][k] = inFs.read();
                outImage[i][k] = inImage[i][k];
            }
        }
        inFs.close();
    }

    Photo() {
        // 생성자-메뉴 추가, 패널을 부착한다.
        setTitle("이미지 처리 프로그램");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = getContentPane();

        // 메뉴 추가하기
        addMenu();

        // 패널 추가하기
        DrawImage panel = new DrawImage();
        contentPane.add(panel, BorderLayout.CENTER);

        // 윈도우 창이나 메뉴 틀의 폭까지 고려해서 크기를 조절
        setSize(8 + 512 + 8, 25 + 31 + 512 + 8);
        setVisible(true);

        displayImage();
    }

    // 패널에서 입출력 이미지 출력하기
    class DrawImage extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int R, G, B;
            int i, k;
            for (i = 0; i < 512; i++) {
                for (k = 0; k < 512; k++) {
                    R = G = B = (int) outImage[i][k];
                    g.setColor(new Color(R, G, B));
                    g.drawRect(k, i, 1, 1);
                }
            }
        }
    }

    void displayImage() {
        Graphics g = contentPane.getGraphics();
        contentPane.paintAll(g);
    }

    // 메뉴를 만들거나 이벤트 리스너를 추가한다.
    public void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu photoMenu = new JMenu("사진 처리하기");
        menuBar.add(photoMenu);

        JMenuItem equalAction = new JMenuItem("동일한 사진");
        JMenuItem nevigateAction = new JMenuItem("반전된 사진");
        JMenuItem mirror1Action = new JMenuItem("좌우 대칭 사진");
        JMenuItem mirror2Action = new JMenuItem("상하 대칭 사진");
        JMenuItem saveAction = new JMenuItem("저장하기");
        JMenuItem exitAction = new JMenuItem("프로그램 종료");

        photoMenu.add(equalAction);
        photoMenu.add(nevigateAction);
        photoMenu.add(mirror1Action);
        photoMenu.add(mirror2Action);
        photoMenu.add(saveAction);
        photoMenu.add(exitAction);
        photoMenu.addSeparator();
        photoMenu.add(saveAction);
        photoMenu.add(exitAction);

        // 동일 이미지 처리하기
        equalAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                equal();
            }
        });

        // 반전 영상 처리하기
        nevigateAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                nevigate();
            }
        });

        // 좌우 대칭 처리
        mirror1Action.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                mirror1();
            }
        });

        // 상하 대칭 처리
        mirror2Action.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                mirror2();
            }
        });

        // 파일 저장하기
        saveAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                saveImage();
            }
        });

        exitAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
    }

    // 영상 처리하기
    // 이미지를 동일하게 처리하는 equal()메소드
    void equal() {
        int i, k;
        for(i = 0; i<512; i++)
            for(k = 0; k<512; k++)
                outImage[i][k] = inImage[i][k];
    displayImage();
    }

    /*
        영상 처리 이미지 반전 처리하는 nevigate()메소드
    */
    void nevigate() {
        int i, k;
        for(i = 0; i < 512; i++)
            for(k = 0; k < 512; k++)
                outImage[i][k] = inImage[i][k];
        displayImage();
    }
    /*
        이미지 좌우 대칭 처리하는 mirror1()메소드
     */
    void mirror1() {
        int i, k;
        for(i = 0; i < 512; i++)
            for(k = 0; k < 512; k++)
                outImage[i][k] = inImage[i][511 - k];
        displayImage();
    }

    /*
        이미지 상하 대칭 처리하는 mirror2()메소드
     */
    void mirror2() {
        int i, k;
        for(i = 0; i < 512; i++)
            for(k = 0; k < 512; k++)
                outImage[i][k] = inImage[511 - i][k];
        displayImage();
    }

    // 이미지 처리 결과를 저장하는 saveImage()메소드
   public void saveImage() {
       int i, k;

       /*
            파일 핸들 또는 스트림 핸들 기능을 추가한다.
        */
       String newFname = "C:\\temp\\result.raw";
       File outFp;
       FileOutputStream outFs;

       outFp = new File(newFname);

       // 저장할 파일 스트림
       try{
           outFs = new FileOutputStream(outFp.getPath());
           // 메모리에서 파일으로
           for(i = 0; i < 512; i++){
               for(k = 0; k < 512; k++) {
                   outFs.write(outImage[i][k]);
               }
           }
           outFs.close();
           JOptionPane.showMessageDialog(null,"파일 저장 성공","파일 저장",
                   JOptionPane.INFORMATION_MESSAGE);
       } catch(Exception e) {
           e.printStackTrace();
       }
   }
}
