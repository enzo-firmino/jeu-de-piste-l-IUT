package tools;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.Batiment;
import model.Enigme;

public class DataBaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Building.db";
    private static final int DATABASE_VERSION = 1;
    private static final String insert = "insert into building (id,name,description) VALUES (1,'batTest','Il s.agit d.un test');";

    public DataBaseManager (Context context) {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL = "create table building (" +
                     "id integer primary key," +
                     " name text not null," +
                     " description text not null" +
                     ")";
        String SQL2 = "create table buildingE (" +
                "id integer primary key," +
                " name text not null," +
                " description text not null" +
                ")";
        String SQL3 = "create table buildingA (" +
                "id integer primary key," +
                " name text not null," +
                " description text not null" +
                ")";
        String SQL4 = "create table enigme (" +
                "id integer primary key," +
                " name text not null," +
                " reponse1 text not null," +
                " reponse2 text not null," +
                " reponse3 text not null," +
                " reponse integer not null" +
                ")";
        String SQL5 = "create table enigmeE (" +
                "id integer primary key," +
                " name text not null," +
                " reponse1 text not null," +
                " reponse2 text not null," +
                " reponse3 text not null," +
                " reponse integer not null" +
                ")";
        String SQL6 = "create table enigmeA (" +
                "id integer primary key," +
                " name text not null," +
                " reponse1 text not null," +
                " reponse2 text not null," +
                " reponse3 text not null," +
                " reponse integer not null" +
                ")";

        db.execSQL(SQL);
        db.execSQL(SQL2);
        db.execSQL(SQL3);
        db.execSQL(SQL4);
        db.execSQL(SQL5);
        db.execSQL(SQL6);
        Log.i("DATABASE","onCREATE");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table building";
        db.execSQL(sql);
        String sql2 = "drop table buildingE";
        db.execSQL(sql2);
        String sql3 = "drop table buildingA";
        db.execSQL(sql3);
        String sql4 = "drop table enigme";
        db.execSQL(sql4);
        String sql5 = "drop table enigmeE";
        db.execSQL(sql5);
        String sql6 = "drop table enigmeA";
        db.execSQL(sql6);
        this.onCreate(db);
        Log.i("DATABASE","onUpgrade");
    }

    public void insertBatiment(int id, String name, String description, String langue) {
        name = name.replace("'","''");
        String sql;
        if (langue.equals("E")) {
            sql = "insert into buildingE (id,name,description) values (" +
                    id + ",'" + name + "','" + description + "')";
        }
        else if (langue.equals("A")){
            sql = "insert into buildingA (id,name,description) values (" +
                    id + ",'" + name + "','" + description + "')";
        }
        else {
            sql = "insert into building (id,name,description) values (" +
                    id + ",'" + name + "','" + description + "')";
        }
        this.getWritableDatabase().execSQL(sql);
        Log.i("DATABASE","insertBAT");
    }

    public void insertEnigme(int id, String name, String reponse1, String reponse2, String reponse3, int reponse, String langue) {
        name = name.replace("'","''");
        String sql;
        if (langue.equals("E")) {
            sql = "insert into enigmeE (id,name,reponse1,reponse2,reponse3,reponse) values (" +
                    id + ",'" + name + "','" + reponse1 + "','" + reponse2 + "','" + reponse3 + "','" + reponse +"')";
        }
        else if (langue.equals("A")){
            sql = "insert into enigmeA (id,name,reponse1,reponse2,reponse3,reponse) values (" +
                    id + ",'" + name + "','" + reponse1 + "','" + reponse2 + "','" + reponse3 + "','" + reponse +"')";
        }
        else {
            sql = "insert into enigme (id,name,reponse1,reponse2,reponse3,reponse) values (" +
                    id + ",'" + name + "','" + reponse1 + "','" + reponse2 + "','" + reponse3 + "','" + reponse +"')";
        }
        this.getWritableDatabase().execSQL(sql);
        Log.i("DATABASE","insertEnigme");
    }

    public List<Batiment> readBatiment(String langue) {
        List<Batiment> listeBat = new ArrayList<>();
        String strSQL;

        if (langue.equals("E")) {
            strSQL = "select * from buildingE order by id";
        }
        else if (langue.equals("A")){
            strSQL = "select * from buildingA order by id";
        }
        else{
            strSQL = "select * from building order by id";
        }
        Cursor cursor  = this.getReadableDatabase().rawQuery(strSQL, null);
        cursor.moveToFirst();
        while(! cursor.isAfterLast()) {
            Batiment batiment = new Batiment(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
            listeBat.add(batiment);
            cursor.moveToNext();
        }
        cursor.close();
        return listeBat;
    }

    public List<Enigme> readEnigme(String langue, int idBat) {
        List<Enigme> listeEnigme = new ArrayList<>();
        String strSQL;

        if(langue.equals("E")) {
            strSQL = "select * from enigmeE where id =" + idBat;
        }
        else if (langue.equals("A")) {
            strSQL = "select * from enigmeA where id =" + idBat;
        }
        else {
            strSQL = "select * from enigme where id =" + idBat;
        }
        Cursor cursor = this.getReadableDatabase().rawQuery(strSQL,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Enigme enigme = new Enigme(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5));
            listeEnigme.add(enigme);
            cursor.moveToNext();
        }
        cursor.close();
        return listeEnigme;
    }


    public void mettreAJour() {
        this.insertBatiment(0, "A", "Le  bâtiment A est commun à tous les départements de l’IUT. On peut y trouver l’accueil ainsi que toute la partie administration d’un côté, la cafétéria et  l’infirmerie de l’autre et la bibliothèque et les classes d’anglais aux étages. \n Il y a aussi deux amphis utilisés par presque tous les départements. C’est ici qu’est organisé certains évènements de l’IUT comme le forum des entreprises, la journée en anglais, et pleins d’autres.\n On peut apercevoir certains jours des stands mis en place par des étudiants de vente de gâteaux, bar à ongles, pour récolter de l’argent pour leurs projets ou pour des associations.","F");
        this.insertBatiment(0, "A", "Building A is common to all departments of the IUT. You can find the reception area as well as the whole administration side on one side, the cafeteria and the infirmary on the other side and the library and English classes on the upper floors.\nThere are also two amphitheaters used by almost all departments. It is here that certain events of the IUT are organized, such as the business forum, the day in English, and many others.\nWe can see some days stands set up by students selling cakes, nail bar, to collect money for their projects or for associations","A");
        this.insertBatiment(0, "A", "El edificio A es común a todos los departamentos de la IUT. Puede encontrar el área de recepción, así como todo el lado de administración en un lado, la cafetería y la enfermería en el otro lado y la biblioteca y las clases de inglés en los pisos superiores.\n También hay dos anfiteatro utilizados por casi todos los departamentos. Es aquí donde se organizan ciertos eventos de la IUT, como el foro de negocios, el día en inglés y muchos otros. \nEn ciertos días, podemos ver puestos instalados por estudiantes que venden pasteles, barras de uñas, para recaudar dinero para sus proyectos o para asociaciones.","E");

        this.insertBatiment(1, "E", "Vous vous trouvez actuellement dans le bâtiment E, utilisé par le département Génie Biologique. Il a été inauguré en 1973 et accueille depuis entre 350 et 400 étudiants tous les ans.\n Ils ont le choix entre plusieurs options comme Analyse biologique et biochimique, Industrie alimentaire et biologique ou encore Diététique. \nIls réalisent aussi des projets au cours de leurs études comme la réalisation de bières artisanales et la création d’un produit choisi par les étudiants (cosmétiques, alimentaires, etc.). \nDans ce bâtiment vous pourrez observer une salle de microbiologie, une cuisine et des cultures cellulaires.","F");
        this.insertBatiment(1, "E", "You are currently in building E, used by the Biological Engineering department. It was inaugurated in 1973 and has since welcomed between 350 and 400 students each year. \nThey have the choice between several options like Biological and biochemical analysis, Food and organic industry or Dietetics. \nThey also carry out projects during their studies such as the production of craft beers and the creation of a product chosen by the students (cosmetics, food, etc.). \nIn building E you can observe a microbiology room, a kitchen and cell cultures.","A");
        this.insertBatiment(1, "E", "Actualmente se encuentra en el edificio E, utilizado por el departamento de Ingeniería Biológica. Fue inaugurado en 1973 y desde entonces ha recibido entre 350 y 400 estudiantes cada año.\n Pueden elegir entre varias opciones como análisis biológicos y bioquímicos, industria alimentaria y orgánica o dietética.\n También llevan a cabo proyectos durante sus estudios, como la producción de cervezas artesanales y la creación de un producto elegido por los estudiantes (cosméticos, alimentos, etc.).\n En este edificio se puede observar una sala de microbiología, una cocina y cultivos celulares.","E");

        this.insertBatiment(2, "G", "Le bâtiment G, nommé le Hall de chimie, est consacré aux étudiants en Chimie. C’est ici qu’ils étudient le génie chimique.\n Vous trouverez donc tous les appareillages nécessaires à cette discipline. Le génie chimique désigne l’application de la chimie physique à l’échelle industrielle. \nLe génie chimique doit faire appel à un vaste domaine de connaissances en chimie mais aussi en physique, électricité, mécanique, techniques... \nCependant, il faut aussi qu’ils prennent en compte les problèmes économiques comme le prix de revient, les problèmes d’hygiène, de sécurité et d’environnement","F");
        this.insertBatiment(2, "G", "Building G, called the Chemistry Hall, is dedicated to chemistry students. This is where they study chemical engineering. \nYou will therefore find all the equipment necessary for this discipline. Chemical engineering refers to the application of physical chemistry on an industrial scale.\n Chemical engineering must call on a vast field of knowledge in chemistry but also in physics, electricity, mechanics, techniques...\nHowever, they must also take into account economic problems such as cost price, hygiene problems , safety and environment...","A");
        this.insertBatiment(2, "G", "El Edificio G, llamado Salón de Química, está dedicado a los estudiantes de química. Aquí es donde estudian ingeniería química.\n Por lo tanto, encontrará todo el equipo necesario para esta disciplina. La ingeniería química se refiere a la aplicación de la química física a escala industrial.\n La ingeniería química debe recurrir a un vasto campo de conocimiento en química, pero también en física, electricidad, mecánica, técnicas ... \n Sin embargo, también deben tener en cuenta los problemas económicos, como el precio de costo, los problemas de higiene. , seguridad y medio ambiente","E");

        this.insertBatiment(3, "F", "Le bâtiment F est dédié aux salles de travaux dirigés pour les étudiants en Chimie. 40% de leur temps est utilisé pour les cours. \nIls étudient principalement la chimie, le génie chimique et la physique, et sont répartis par groupe de 24 à 28 étudiants.","F");
        this.insertBatiment(3, "F", "Building F is dedicated to tutorial rooms for chemistry students. 40% of their time is used for lessons. \nThey mainly study chemistry, chemical engineering and physics, and are divided into groups of 24 to 28 students.","A");
        this.insertBatiment(3, "F", "El Edificio F está dedicado a salas de tutoría para estudiantes de química. El 40% de su tiempo se usa para clases. \nEstudian principalmente química, ingeniería química y física, y se dividen en grupos de 24 a 28 estudiantes.","E");

        this.insertBatiment(4, "H", "Le bâtiment H est le troisième et dernier bâtiment attribué aux Chimie. Il comporte deux étages. \nAu rez-de-chaussé, on étudie la chimie analytique à l’aide de machines pour réaliser les analyses. La chimie analytique est une partie de la chimie qui concerne l’analyse des produits, c’est-à-dire l’identification et la caractérisation de substances chimiques connues ou non. Au deuxième étage, on étudie la chimie inorganique, autrement appelé chimie minérale. On peut trouver dans cet étage des fours un peu particulier...\n Enfin, au dernier étage on étudie la chimie organique, qui est la partie de la chimie qui traite des substances naturelles ou synthétiques formées de carbone.","F");
        this.insertBatiment(4, "H", "Building H is the third and last building attributed to Chemistry. It has two floors. \nOn the ground floor, we study analytical chemistry using machines to perform the analyzes. Analytical chemistry is a part of chemistry that concerns the analysis of products, that is to say the identification and characterization of chemicals, known or not. On the second floor, we study inorganic chemistry, otherwise called mineral chemistry. We can find in this floor a little special ovens...\nFinally, on the top floor we study organic chemistry, which is the part of chemistry that deals with natural or synthetic substances formed of carbon.","A");
        this.insertBatiment(4, "H", "El edificio H es el tercer y último edificio atribuido a la química. Tiene dos pisos.\n En la planta baja, estudiamos química analítica utilizando máquinas para realizar los análisis. La química analítica es una parte de la química que se refiere al análisis de productos, es decir, la identificación y caracterización de productos químicos, conocidos o no. En el segundo piso, estudiamos química inorgánica, también llamada química mineral. Podemos encontrar en este piso unos pequeños hornos especiales ...\nFinalmente, en el piso superior estudiamos química orgánica, que es la parte de la química que trata con sustancias naturales o sintéticas formadas de carbono.","E");

        this.insertBatiment(5, "I & J", "Les Bâtiments I et J sont occupés par le département GEII. \n Depuis 1967, le campus accueille plus de 300 étudiants Genie Electrique et Informatique Industrielle en DUT et en license professionnelle.\n Ils étudient principalement l’électronique, l’électrotechnique et l’informatique industrielle. Ces étudiants réalisent tout au long de l’année différents projet comme des concours robots, rover mars, réseau LoRa, ampli audio, skate électrique.\n Le taux de réussite pour le DUT est d’environ 55%. ","F");
        this.insertBatiment(5, "I & J", "Buildings I and J are occupied by the GEII department.\n Since 1967, the campus has welcomed more than 300 Electrical Engineering and Industrial Computer Science students with a DUT and a professional license.\n They mainly study electronics, electrical engineering and industrial computing. These students carry out various projects throughout the year such as robot contests, rover mars, LoRa network, audio amplifier, electric skateboard.\n The success rate for the DUT is around 55%.","A");
        this.insertBatiment(5, "I & J", "Los edificios I y J están ocupados por el departamento de GEII.\n Desde 1967, el campus ha recibido a más de 300 estudiantes de Ingeniería Eléctrica e Informática Industrial con un DUT y una licencia profesional.\n Estudian principalmente electrónica, ingeniería eléctrica y computación industrial. Estos estudiantes llevan a cabo varios proyectos durante todo el año, como concursos de robots, rover marte, red LoRa, amplificador de audio, patineta eléctrica.\n La tasa de éxito para el DUT es de alrededor del 55%.","E");

        this.insertBatiment(6,"B", "Le bâtiment B est un bâtiment Mesure Physiques. Il regroupe toutes les salles de travaux dirigés, c’est donc  ici que les étudiants en Mesure Physiques suivent leurs cours.\n Il y a cependant quelque chose d’anecdotique dans ce bâtiment… saurez-vous deviner de quoi il s’agit?","F");
        this.insertBatiment(6, "B", "Building B is a Physical Measurement building. It brings together all the tutorial rooms, so this is where Physical Measurement students take their lessons.\n There is, however, something anecdotal about this building ... can you guess what it is?\n","A");
        this.insertBatiment(6, "B", "El edificio B es un edificio de medición física. Reúne todas las salas de tutoría, así que aquí es donde los estudiantes de Medición Física toman sus lecciones.\n Sin embargo, hay algo anecdótico en este edificio ... ¿puedes adivinar de qué se trata?\n","E");

        this.insertBatiment(7, "C", "Le bâtiment C appartient au département de Mesures Physique. Ce bâtiment est le premier à avoir été construit dans le campus, en 1968.\n En Mesures Physiques, les étudiants réalisent de nombreuses manipulations physiques. Ils étudient notamment l’électronique, l’électricité, la mécanique, l’optique, les matériaux et la thermodynamique.\n Ils y a environ 230 étudiants en DUT Mesures Physique et environ 65% d’entre eux obtiendront leur diplômes en 2 ans.","F");
        this.insertBatiment(7, "C", "Building C belongs to the Department of Physical Measurements. This building is the first to have been built on campus in 1968.\nIn Physical Measurements, students carry out numerous physical manipulations. They study in particular electronics, electricity, mechanics, optics, materials and thermodynamics.\nThere are around 230 students in DUT Physical Measurements and around 65% of them will graduate in 2 years.","A");
        this.insertBatiment(7, "C", "El Edificio C pertenece al Departamento de Mediciones Físicas. Este edificio es el primero que se construyó en el campus en 1968.\n En mediciones físicas, los estudiantes realizan numerosas manipulaciones físicas. Estudian en particular electrónica, electricidad, mecánica, óptica, materiales y termodinámica.\nHay alrededor de 230 estudiantes en medidas físicas de DUT y alrededor del 65% de ellos se graduarán en 2 años.\n","E");

        this.insertBatiment(8, "D", "Dans le bâtiment D vous trouverez principalement des salles de cours et d’informatiques. Mais aussi un lieu clé de l’IUT: Ob.i LAB.\n C’est un espace où il est mis à disposition toutes sortes d’outils manuels et numériques pour la conception et la réalisation d’objets.\n C’est un endroit d’expérimentations, de création et d’enrichissement de connaissances. \nIl peut être utilisé par l’ensemble des étudiants et du personnel de toutes les filières de l’IUT Montpellier-Sète, mais est aussi ouvert sur le monde professionnel : entrepreneurs, artistes, chercheurs, ou bricoleurs peuvent venir fabriquer leurs idées pour passer plus rapidement de la phase de concept à la phase de prototypage.","F");
        this.insertBatiment(8, "D", "In building D, you will mainly find classrooms and computer rooms. But you will also find a key place of the IUT: Ob.i LAB. \nIt is a space where it is made available all kinds of manual and digital tools for the design and creation of objects.\n It is a place for experimentation, creation and enrichment of knowledge. \nIt can be used by all students and staff from all sectors of the IUT Montpellier-Sète, but is also open to the world. professional: entrepreneurs, artists, researchers, or DIY enthusiasts can come and build their ideas to move more quickly from the concept phase to the prototyping phase.","A");
        this.insertBatiment(8, "D", "En el edificio D, encontrarás principalmente aulas y aulas de informática. Pero también encontrará un lugar clave del IUT: Ob.i LAB. \nEs un espacio donde se pone a disposición todo tipo de herramientas manuales y digitales para el diseño y creación de objetos. \nEs un lugar para la experimentación, la creación y el enriquecimiento del conocimiento. \nPuede ser utilizado por todos los estudiantes y el personal de todos los sectores del IUT Montpellier-Sète, pero también está abierto al mundo. profesional: empresarios, artistas, investigadores o entusiastas del bricolaje pueden venir y construir sus ideas para pasar más rápidamente de la fase de concepto a la fase de creación de prototipos.","E");

        this.insertBatiment(9, "M", "Ce Bâtiment, appelé la halle, est un bâtiment utilisé pour de la transformation alimentaire. \nIl est utilisé partiellement par les étudiants en Génie Biologique, mais aussi par des professionnels qui peuvent le louer. ","F");
        this.insertBatiment(9, "M", "This building, called the hall, is a building used for food processing. \nIt is partially used by students in Biological Engineering, but also by professionals who can rent it.\n","A");
        this.insertBatiment(9, "M", "Este edificio, llamado hall, es un edificio utilizado para el procesamiento de alimentos. \nEs parcialmente utilizado por estudiantes de Ingeniería Biológica, pero también por profesionales que pueden alquilarlo.\n","E");

        this.insertBatiment(10, "K", "Le bâtiment K abrite trois départements : Informatique, Technique de Commercialisation et Gestion des Entreprises et Administrations. Au rez-de-chaussé vous trouverez des salles de TD .\n" +
                "Le département informatique se trouve au premier étage. En DUT informatique nous étudions différents langages de programmation comme par exemple  JAVA, HTML CSS, Python et bien d’autres. Nous étudions aussi les bases de donnée, le réseau, les systèmes d’exploitation. Les étudiants utilisent les salles informatiques en libre service pour avancer sur leurs projets quand ils le souhaitent. Vous pouvez observer les projets créer par les étudiants en informatique en vous rendant sur le site internet de l’IUT, l’intranet ou encore… cette application! Nous pouvons au cours de l’année participer à des évènements proposés par le département comme la nuit de l’info qui est un concours où les étudiants passent une nuit entière à coder sur un sujet donné, ou encore la code game jam, un concours de programmation de jeux vidéo.\n" +
                "\n" + "\n" +
                "Le deuxième étage accueille donc le département Technique de Commercialisation (TC) et le département Gestion des Entreprises et Administrations (GEA).\n" +
                "Il y a plus ou moins 115 étudiants par an en TC qui étudient principalement le marketing, la communication, la distribution et la négociation. Ils effectuent deux stages, un de deux semaines en première année et un autre de huit semaines en deuxième année. durant leurs projets tutoré, certains participent au projet bière en collaboration avec les Genie Biologique, en s’occupant de créer l’identité du produit. Ils y a aussi des activités créer pour les étudiants en TC comme un concours de publicité et des rencontres avec des banques.\n" +
                "\n" +
                "Les TC partagent donc le deuxième étage avec les GEA.\n" +
                "En effet, les étudiants en GEA suivent un enseignement plus centré sur le droit et la comptabilité. Ils étudient aussi l’économie, le management et la fiscalité. En deuxième année, ils doivent choisir entre trois options: option Gestion Comptable et Financière (GCF), option Gestion et Management des Organisations (GMO) et option Gestion des Ressources Humaines (GRH).","F");
        this.insertBatiment(10, "K", "Building K houses three departments: IT, Marketing Technology and Business Management and Administration. On the ground floor you will find TD rooms.\n" +
                "The IT department is on the first floor. In IT DUT we study different programming languages such as JAVA, HTML CSS, Python and many others. We also study databases, network, operating systems Students use the self-service computer rooms to advance their projects when they want to. You can observe the projects created by computer students by going to the IUT website, the intranet or even… During the year we can participate in events offered by the department such as the Info Night which is a contest where students spend an entire night coding on a given subject, or even the game jam code, a video game programming contest.\n" +
                                "\n" + "\n" +
                                "The second floor therefore houses the Technical Marketing Department (TC) and the Business Management and Administration Department (GEA).\n" +
                                "There are more or less 115 students per year in TC who mainly study marketing, communication, distribution and negotiation. They do two internships, one of two weeks in first year and another of eight weeks in second year. during their tutored projects, some participate in the beer project in collaboration with Genie Biologique, taking care of creating the product identity. There are also activities to create for TC students such as an advertising contest and meetings with banks.\n" +
                                "\n" +
                        "The TC therefore share the second floor with the GEA.\n" +
                                "Indeed, GEA students follow a more focused education on law and accounting. They also study economics, management and taxation. In the second year, they must choose between three options: Accounting and Financial Management option ( GCF), option Management and Management of Organizations (GMO) and option Management of Human Resources (GRH).","A");
        this.insertBatiment(10, "K", "El edificio K alberga tres departamentos: TI, tecnología de marketing y gestión y administración de empresas. En la planta baja encontrará salas de TD.\n" +
                "El departamento de TI está en el primer piso. En IT DUT estudiamos diferentes lenguajes de programación como JAVA, HTML CSS, Python y muchos otros. También estudiamos bases de datos, redes y sistemas operativos. Los estudiantes usan las salas de computadoras de autoservicio para avanzar en sus proyectos cuando lo deseen. Puede observar los proyectos creados por los estudiantes de computadoras yendo al sitio web de IUT, la intranet o incluso ... Durante el año, podemos participar en eventos ofrecidos por el departamento, como la Noche de información, que es un concurso en el que los estudiantes pasan una noche entera codificando un tema determinado o el código de bloqueo del juego, un concurso de programación de videojuegos. \n" +
                "\n" + "\n" +
                "Por lo tanto, el segundo piso alberga el Departamento de Marketing Técnico (TC) y el Departamento de Administración y Administración de Empresas (GEA).\n" +
                "Hay más o menos 115 estudiantes por año en CT que estudian principalmente marketing, comunicación, distribución y negociación. Hacen dos pasantías, una de dos semanas en el primer año y otra de ocho semanas en el segundo año. Durante sus proyectos tutorizados, algunos participan en el proyecto de la cerveza en colaboración con Genie Biologique, encargándose de crear la identidad del producto. También hay actividades para crear para los estudiantes de CT, como un concurso publicitario y reuniones con bancos.\n" +
                "\n" +
                "Los TC, por lo tanto, comparte el segundo piso con el GEA.\n" +
                "De hecho, los estudiantes de GEA siguen una educación más enfocada en derecho y contabilidad. También estudian economía, administración e impuestos. En el segundo año, deben elegir entre tres opciones: opción de contabilidad y administración financiera ( GCF), opción Gestión y Gestión de Organizaciones (OMG) y opción Gestión de Recursos Humanos (GRH).","E");

        this.insertBatiment(11, "L", "Le bâtiment L contient uniquement 2 amphis : les amphis 3 et 4.","F");
        this.insertBatiment(11, "L", "Building L only contains 2 amphitheater : amphitheaters 3 and 4.","A");
        this.insertBatiment(11, "L", "El edificio L solo contiene 2 anfiteatros : anfiteatros 3 y 4.","E");



        this.insertEnigme(0,"A quel étage se trouve la bibliothèque ?","Rez-de-chaussée","1er étage","2ème étage",2,"F");
        this.insertEnigme(0,"On which floor is the library ?","Ground Floor","1st floor","2nd floor",2, "A");
        this.insertEnigme(0,"¿ En qué piso está la biblioteca ?","Planta baja","1ro piso","2do piso",2, "E");

        this.insertEnigme(1,"De quelle couleur la devanture du bâtiment est-elle ?","Verte","Bleue","Jaune",2, "F");
        this.insertEnigme(1,"What color is the front of the building ?","Green","Blue","Yellow",2, "A");
        this.insertEnigme(1,"¿De qué color es el frente del edificio?","Verde","Azùl","Amarillo",2, "E");

        this.insertEnigme(2,"Que désigne génie chimique ?","C’est de la chimie à échelle industrielle","C’est de la chimie à petite échelle","C’est de la chimie pour les génies",1, "F");
        this.insertEnigme(2,"What does chemical engineering mean ?","It’s industrial scale chemistry","It’s small-scale chemistry","It’s chemistry for geniuses",1, "A");
        this.insertEnigme(2,"¿Qué significa la ingeniería química?","Es química a escala industrial","Es química a pequeña escala","Es química para genios",1, "E");

        this.insertEnigme(3,"Quelle matière les étudiants en Chimie n’étudient pas ?","Informatique","Hygiène, Sécurité, Environnement","Anatomopathologie",3, "F");
        this.insertEnigme(3,"What subject do chemistry students not study ?","Computer science","Health Safety Environment","Pathology",3, "A");
        this.insertEnigme(3,"¿Qué asignatura no estudian los estudiantes de química?","Proceso de datos","Medio Ambiente Seguridad y Salud","Patología",3, "E");

        this.insertEnigme(4,"Qu’est-ce que les fours du premier étage ont de particulier ?","Ils sont tout petits","Ils chauffent jusqu’à 900°c","Ils chauffent au feu de bois",2, "F");
        this.insertEnigme(4,"What is special about the ovens on the first floor ?","They are very small","They heat up to 900°c","They heat over a wood fire",2, "A");
        this.insertEnigme(4,"¿Qué tienen de especial los hornos en el primer piso?","Son muy pequeños","Calientan hasta 900°c.","Calientan sobre un fuego de leña.",2, "E");

        this.insertEnigme(5,"Pour quel département le bâtiment J était initialement prévu ?","Génie Biologique","Mesures Physiques","Informatique",2, "F");
        this.insertEnigme(5,"For which department building J was initially planned ?","Biological Engineering","Physical Measurements","Computer Science",2, "A");
        this.insertEnigme(5,"¿Para qué departamento estaba planeado originalmente el edificio J?","Genio Biológico","Medidas Físicas","Informatica",2, "E");

        this.insertEnigme(6,"Où se cache le train des Mesures Physiques ?","Rez-de-chaussée","1er étage","2ème étage",3, "F");
        this.insertEnigme(6,"Where is the Train of Physical Measurements hidden ?","Ground floor","1st floor","2nd floor",3, "A");
        this.insertEnigme(6,"¿Dónde está escondido el tren de medidas físicas?","Planta baja","1ro piso","2do piso",3, "E");

        this.insertEnigme(7,"Quelle machine renferme le bâtiment B dans une de ses salle ?","Soufflerie","Imprimante 3D","A laver",1, "F");
        this.insertEnigme(7,"Which machine contains building B in one of its rooms ?","Wind tunnel","3D printer","Washing one",1, "A");
        this.insertEnigme(7,"¿Qué máquina contiene el edificio B en una de sus habitaciones?","Túnel de viento","Impresora 3d","Para lavar",1, "E");

        this.insertEnigme(8,"Que signifie Ob.i LAB ?","’Ob’ pour observer, ‘i’ pour imaginer, et ‘LAB’ pour laboratoire","’Ob’ pour obligatoire, ‘i’ pour interpréter, et ‘LAB’ pour labrador","’Ob’ pour objets, ‘i’ pour intelligent, et ‘LAB’ pour laboratoire",3, "F");
        this.insertEnigme(8,"What does Ob.i LAB mean ?","’Ob ’to observe,‘ i ’to imagine, and‘ LAB ’to laboratory","’Ob’ for compulsory,‘ i ’for interpreting, and‘ LAB ’for labrador","’Ob’ for objects,‘ i ’for intelligent, and‘ LAB ’for laboratory",3, "A");
        this.insertEnigme(8,"¿Qué significa Ob.i LAB?","’Ob ’para observar,’ yo ’para imaginar, y’ LAB ’para laboratorio","’Ob’ para obligatorio,‘ i ’para interpretación y‘ LAB ’para labrador","’Ob’ para objetos, ’i’ para inteligente y ’LAB’ para laboratorio",3, "E");

        this.insertEnigme(9,"Pour quoi est utilisé ce bâtiment ?","Transformation alimentaire","Salle de chimie","Garage",1, "F");
        this.insertEnigme(9,"What is this building used for ?","Food processing","Chemistry lab room","Garage",1, "A");
        this.insertEnigme(9,"¿Para qué se usa este edificio?","Procesamiento de alimentos","Sala de laboratorio de química","Garaje",1, "E");

        this.insertEnigme(10,"Quelle est la différence entre le département TC et GEA ?","C’est la même chose","Il y a peu de marketing et beaucoup de droit en GEA","Il y a peu de marketing et beaucoup de droit en TC",2, "F");
        this.insertEnigme(10,"What is the difference between TC and GEA ?","Same things","there is little marketing and a lot of law in GEA","There is little marketing and a lot of law in TC",2, "A");
        this.insertEnigme(10,"¿Cuál es la diferencia entre TC y GEA?","Es lo mismo","Hay poca comercialización y mucha ley en GEA","Hay poca comercialización y muchos derechos en TC",2, "E");

        this.insertEnigme(11,"Quels amphis contient le bâtiment L","1 & 2","5 & 6","3 & 4",3, "F");
        this.insertEnigme(11,"Which amphitheater contains building L ?","1 & 2","5 & 6","3 & 4",3, "A");
        this.insertEnigme(11,"¿Qué anfiteatro contiene el edificio L?","1 & 2","5 & 6","3 & 4 ",3, "E");


    }

    public void vider() {
        String sql = "delete from building";
        this.getWritableDatabase().execSQL(sql);
        String sql2 = "delete from buildingE";
        this.getWritableDatabase().execSQL(sql2);
        String sql3 = "delete from buildingA";
        this.getWritableDatabase().execSQL(sql3);
        String sql4 = "delete from enigmeE";
        this.getWritableDatabase().execSQL(sql4);
        String sql5 = "delete from enigmeA";
        this.getWritableDatabase().execSQL(sql5);
        String sql6 = "delete from enigme";
        this.getWritableDatabase().execSQL(sql6);
    }

    public int count(String langue) {
        String sql;
        if (langue.equals("A")) {
            sql = "select count(*) from buildingA";
        }
        else if (langue.equals("E")){
            sql = "select count(*) from buildingE";
        }
        else {
            sql = "select count(*) from building";
        }
        Cursor cursor = this.getReadableDatabase().rawQuery(sql, null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

}
