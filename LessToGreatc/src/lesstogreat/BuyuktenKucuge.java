/*
 * Kullandığım algoritma tamamen bana aittir ve dershaneye giderken,
 * yolda aklıma gelmiştir.
 */
package lesstogreat;

import java.util.LinkedList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Kodu yazarken bazı değişken isimlerini ingilizce yaptım çünkü bir işlemin,
 * nasıl olması gerektiğini düşünürken ingilizce düşünmem gerekebiliyor.
 * yoksa İngiliz uşağı değilim.
 *
 * @version 1.0
 * @author kayra
 */
public class BuyuktenKucuge {

    public static void main(String[] args) {
        LinkedList <Integer> liste = new LinkedList<>();
        Scanner giris;
        
        //Bu değişkenler Tamam-Devam sistemi için
        int i = 1;
        boolean devam = true;
        Scanner check = new Scanner(System.in);
        String devamMi;
        boolean anlasilmadi = false;
        
        
        
        int recentNum = 0;
        int biggestNum = 0;
        int smallestNum = 0;
        
        boolean basla = true;
        int input = 0;
        
        
        int j = 0;
        
        //Input un sayi olup olmadığı kontrolü
        boolean isNumber;
        
        System.out.print("Lütfen sıralanacak sayıları giriniz:");
        
        allCut:
        while (devam){
            
            anlasilmadi = false;
            isNumber = false;
            
            while(!isNumber){
                try{
                    //Basit bir kullanıcıdan veri alma sistemi ve buna bağlı error handling.
                    
                    System.out.print("\n"+ i + ". Sayi: ");
                    
                    //giris Scannerını her seferinde tekrardan initialize etmek en mantıklı seçenek geldi.
                    //Bu şekilde giris.reset() yapmadan reset edebiliyorum girilen sayıları.
                    //Yani her seferinde yeni bi giris nesnesi tanımlıyorum. Mantıklı, di mi? :)
                    
                    giris = new Scanner(System.in);
                    input = giris.nextInt();
                    isNumber = true;
                    
                    recentNum = input;
                }
                catch(InputMismatchException e){
                    System.out.println("Lütfen Sayı girin.");
                    isNumber = false;
                }
            }
            
            //Bir kere çalışır
            if(basla){
                smallestNum = recentNum;
                basla = false;
            }       
            
            //Küçükse en küçüğe eşitle, büyükse en büyüğe eşitle.
            if (recentNum < smallestNum){
                smallestNum = recentNum;
                liste.addFirst(smallestNum);
            }
            else if (recentNum > biggestNum){
                biggestNum = recentNum;
                liste.addLast(biggestNum);
            }
            
            //3.Sayıdan sonra artık sayının yerini bulmak için:
            else if (recentNum > smallestNum && recentNum < biggestNum){
                //Girdiğimiz sayıyı sırayla listenin başından başlayarak büyük mü değil mi karşılaştırır.
                while(recentNum > liste.get(j)){
                    j++;
                }
                
                /*
                * Büyük olmadığı bir ana gelince (bu durum eşitlikte olabilir) eğer sayımız,
                * listemizin bulunduğumuz kısmındaki (j indeksi) sayıya eşitse
                * sayımızı, onun yanına ekler.
                * Eğer değilse, yani o kısımda eşit bir sayı bulunmuyorsa, j indeksine sayımızı atar,
                * ve j' yi 0 eşitler ki işlem tekrarlanabilsin.
                */
                if(recentNum == liste.get(j)){
                    liste.add(j, recentNum);
                }else{
                    liste.add(j, recentNum);
                    j = 0;
                }
            }
            //Bu kısmı ekledim çünkü en baştaki sayıyı her nedense algılamıyordu.
            //Bununla en küçük sayı girilen sayıyla eşitse inputğimiz sayıyı en küçüğün yanına ekler.
            else if(recentNum == smallestNum){
                liste.add(0, recentNum);
            }
            
            
            /*
             * Burdaki anlasilmadi olayı "Tamam-Devam" sistemidir.
             * anlasilmadi = true ise anlaşılmamıştır tam tersinde anlaşılmıştır.
            */
            cut:
            while (!anlasilmadi) {
                //2 sayıdan sonra devam edip etmeyeceğimizi sorar
                if (i%2 == 0){
                    System.out.print("\nDevam edilecek mi? (Y or N): ");
                    devamMi = check.next().toString();
                    
                    //Basit bi error handler
                    if (null == devamMi){
                        System.out.println("Anlaşılmadı.");
                        anlasilmadi = true;
                    }
                    else switch (devamMi) {
                        case "Y":
                        case "y":
                            
                            //y yazarsak sadece devam edilecek mi kısmından çıkar.
                            break cut;
                        case "N":
                        case "n":
                            
                            //n yazarsak tamamen bitirir.
                            devam = false;
                            break allCut;
                        default:
                            
                            //girilen şey anlaşılmadıysa...
                            System.out.println("Anlaşılmadı.");
                    }
                }else{
                    //2 sayıdan 2. sini girmediğimizde loopun baştan sarması için.
                    //Bunu kaldırsam da olur, çünkü ben ellemedikçe true olacak.
                    anlasilmadi = true;
                }
            }
            i++;
        }
        System.out.print("\n"+liste);   
    }  
}
