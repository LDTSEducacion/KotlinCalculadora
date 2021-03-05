# Calculadora
Proyecto introductorio a android con kotlin

https://developer.android.com/guide?hl=es-419

Información general sobre los recursos de las aplicaciones
Los recursos son los archivos adicionales y el contenido estático que usa tu código, como mapas de bits, definiciones de diseño, strings de interfaz de usuario, instrucciones de animación, etc.

Siempre debes externalizar los recursos para aplicaciones, como imágenes y strings de tu código, para que puedas mantenerlos de forma independiente. También debes proporcionar recursos alternativos para configuraciones de dispositivos específicos, agrupándolos en directorios de recursos con un nombre especial. En tiempo de ejecución, Android utiliza el recurso adecuado según la configuración actual. Por ejemplo, puedes proporcionar un diseño de interfaz de usuario (IU) diferente según el tamaño de la pantalla o strings diferentes según la configuración de idioma.

Una vez que externalizas los recursos para tu aplicación, puedes acceder a ellos mediante los ID de recursos que se generan en la clase R de tu proyecto. En este documento, se muestra cómo puedes agrupar los recursos en tu proyecto de Android, proporcionar recursos alternativos para configuraciones de dispositivos específicos y acceder a ellos desde el código de tu aplicación u otros archivos XML posteriormente.

Agrupar tipos de recursos
Debes colocar cada tipo de recurso en un subdirectorio específico del directorio res/ de tu proyecto. Por ejemplo, esta es la jerarquía de archivos de un proyecto simple:


MyProject/
    src/
        MyActivity.java
    res/
        drawable/
            graphic.png
        layout/
            main.xml
            info.xml
        mipmap/
            icon.png
        values/
            strings.xml
Como se ve en este ejemplo, el directorio res/ contiene todos los recursos (en subdirectorios): un recurso de imagen, dos recursos de diseño, directorios mipmap/ para los íconos del selector y un archivo de recursos de strings. Los nombres del directorio de recursos son importantes y se describen en la tabla 1.

Nota: Para obtener más información sobre el uso de las carpetas mipmap, consulta Información general sobre la administración de proyectos.

Tabla 1: Directorios de recursos admitidos dentro del directorio res/ del proyecto

Directorio	Tipo de recurso
animator/	Archivos XML que definen animaciones de propiedades.
anim/	Archivos XML que definen animaciones de interpolación de movimiento. En este directorio, también se pueden guardar animaciones de propiedades, pero se prefiere el directorio animator/ para las animaciones de propiedades, a fin de distinguir entre los dos tipos.
color/	Archivos XML que definen una lista de estados de colores. Consulta la sección Recurso de lista de estado de colores.
drawable/	
Archivos de mapas de bits (.png, .9.png, .jpg y .gif) o archivos XML que se han compilado en los siguientes subtipos de recursos de elemento de diseño:

Archivos de mapas de bits
Nueve parches (mapas de bits reajustables)
Listas de estados
Formas
Elementos de diseño de animaciones
Otros elementos de diseño
Consulta la sección Recursos dibujables.

mipmap/	Archivos de elementos de diseño para diferentes densidades de los íconos de selectores. Para obtener más información sobre la administración de los íconos de selectores con carpetas mipmap/, consulta la sección Información general sobre la administración de proyectos.
layout/	Archivos XML que definen el diseño de una interfaz de usuario. Consulta la sección Recurso de diseño.
menu/	Archivos XML que definen menús de aplicaciones, como un menú de opciones, un menú contextual o un submenú. Consulta la sección Recurso de menú.
raw/	
Archivos arbitrarios para guardar sin procesar. Para abrir estos recursos con un objeto InputStream sin procesar, llama a Resources.openRawResource() con el ID del recurso, que es R.raw.filename.

Sin embargo, si necesitas acceder a los nombres de los archivos originales y a la jerarquía de archivos, puedes considerar la posibilidad de guardar algunos recursos en el directorio assets/ (en lugar de res/raw/). A los archivos de assets/ no se les asigna un ID de recurso, por lo cual puedes leerlos solamente mediante AssetManager.

values/	
Archivos XML que contienen valores simples, como strings, valores enteros y colores.

Los archivos de recursos XML en otros subdirectorios res/ definen un único recurso basado en el nombre del archivo XML, mientras que los archivos del directorio values/ describen varios recursos. En el caso de un archivo de este directorio, cada campo secundario del elemento <resources> define un único recurso. Por ejemplo, un elemento <string> crea un recurso R.string, y un elemento <color> crea un recurso R.color.

Dado que cada recurso se define con su propio elemento XML, puedes asignar el nombre que desees al archivo y colocar diferentes tipos de recursos en un archivo. Sin embargo, para mayor claridad, es recomendable que coloques tipos de recursos únicos en diferentes archivos. Por ejemplo, a continuación, se incluyen algunas convenciones de asignación de nombres de archivos para los recursos que puedes crear en este directorio:

arrays.xml para matrices de recursos (matrices escritas).
colors.xml para valores de color.
dimens.xml para valores de dimensión.
strings.xml para valores de strings.
styles.xml para estilos.
Consulta las secciones Recursos de strings, Recursos de estilo y Más tipos de recursos.

xml/	Archivos XML arbitrarios que se pueden leer en tiempo de ejecución llamando a Resources.getXML(). Aquí se deben guardar diversos archivos de configuración XML, por ejemplo, una configuración que permite búsqueda.
font/	Archivos de fuentes, con extensiones como .ttf, .otf o .ttc, o archivos XML que incluyan un elemento <font-family>. Para obtener más información sobre las fuentes como recursos, ve a Fuentes en XML.
Advertencia: Nunca guardes archivos de recursos directamente dentro del directorio res/; de lo contrario, se producirá un error en el compilador.

Para obtener más información sobre ciertos tipos de recursos, consulta la documentación Tipos de recursos.

Los recursos que guardes en los subdirectorios definidos en la tabla 1 son tus recursos "predeterminados". Es decir, son los recursos que definen el diseño y el contenido predeterminados de tu aplicación. Sin embargo, cada tipo de dispositivo con tecnología Android puede necesitar recursos diferentes. Por ejemplo, si un dispositivo tiene una pantalla de un tamaño mayor que el habitual, debes proporcionar recursos de diseño diferentes que aprovechen ese espacio adicional en la pantalla. O bien, si un dispositivo tiene una configuración de idioma diferente, debes proporcionar distintos recursos de strings que traduzcan el texto en su interfaz de usuario. Para proporcionar estos recursos diferentes a distintas configuraciones de dispositivos, debes proporcionar recursos alternativos, además de tus recursos predeterminados.

Proporcionar recursos alternativos
Casi todas las aplicaciones deben proporcionar recursos alternativos para admitir configuraciones de dispositivos específicos. Por ejemplo, debes incluir recursos de elementos de diseño para diferentes densidades de pantallas y recursos de strings alternativos para diferentes idiomas. En tiempo de ejecución, Android detecta la configuración del dispositivo actual y carga los recursos adecuados para tu aplicación.


Figura 1: Dos dispositivos diferentes, cada uno de los cuales usa diferentes recursos de diseño

Si quieres especificar alternativas de configuraciones específicas para un conjunto de recursos:

Crea en res/ un directorio nuevo cuyo nombre tenga el formato <resources_name>-<config_qualifier>.
<resources_name> es el nombre del directorio de los recursos predeterminados correspondientes (definidos en la tabla 1).
<qualifier> es un nombre que especifica una configuración individual para la cual se deben usar estos recursos (que se definen en la tabla 2).
Puedes agregar más de un <qualifier>. Separa cada uno con un guion.

Advertencia: Cuando agregues varios calificadores, debes disponerlos en el mismo orden en el que se enumeran en la tabla 2. Si los calificadores están ordenados de manera incorrecta, los recursos se ignoran.

Guarda los recursos alternativos correspondientes en este nuevo directorio. Los archivos de recursos deben llevar exactamente el mismo nombre que los archivos de recursos predeterminados.
Por ejemplo, estos son algunos recursos predeterminados y alternativos:


res/
    drawable/
        icon.png
        background.png
    drawable-hdpi/
        icon.png
        background.png
El calificador hdpi indica que los recursos de ese directorio son para dispositivos con pantalla de alta densidad. Las imágenes de cada uno de estos directorios de elementos de diseño están dimensionadas para una densidad de pantalla específica, pero los nombres de archivo son exactamente iguales. De este modo, el ID de recurso que usas para hacer referencia a la imagen icon.png o background.png es siempre el mismo, pero Android selecciona la versión de cada recurso que mejor se ajusta al dispositivo actual comparando la información de configuración del dispositivo con los calificadores del nombre del directorio de recursos.

Android admite varios calificadores de configuración, y tú puedes agregar múltiples calificadores a un nombre de directorio separando cada calificador con un guion. En la tabla 2, se enumeran los calificadores de configuración válidos en orden de importancia. Si utilizas varios calificadores para un directorio de recursos, debes agregarlos al nombre del directorio en el orden en el que se enumeran en la tabla.

Tabla 2: Nombres de calificadores de configuración.

Configuración	Valores de calificadores	Descripción
MCC y MNC	Ejemplos:
mcc310
mcc310-mnc004
mcc208-mnc00
etc.	
El código de país de móvil (MCC), opcionalmente seguido del código de red móvil (MNC) de la tarjeta SIM del dispositivo. Por ejemplo, mcc310 se refiere a los Estados Unidos con cualquier operador, mcc310-mnc004 se refiere a los Estados Unidos con Verizon, y mcc208-mnc00 se refiere a Francia con Orange.

Si el dispositivo utiliza una conexión de radio (teléfono GSM), los valores del MCC y el MNC provienen de la tarjeta SIM.

También puedes utilizar el MCC solo (por ejemplo, para incluir recursos legales de países específicos en tu aplicación). Si necesitas especificar solamente en función del idioma, utiliza el calificador de idioma y región (se trata a continuación). Si decides utilizar el calificador de MCC y MNC, debes hacerlo con cuidado y comprobar que funcione de la forma esperada.

También observa los campos de configuración mcc y mnc, que indican el código móvil de país y el código de red móvil actuales, respectivamente.

Idioma y región	Ejemplos:
en
fr
en-rUS
fr-rFR
fr-rCA
b+en
b+en+US
b+es+419
El idioma se define mediante un código de idioma ISO 639-1 de dos letras, opcionalmente seguido de un código de región ISO 3166-1-alfa-2 de dos letras (precedido de "r" en minúscula).

Los códigos no distinguen mayúsculas de minúsculas; el prefijo r se utiliza para distinguir la parte de la región. No se puede especificar una región sola.

Android 7.0 (nivel de API 24) presentó compatibilidad con las etiquetas de idioma BCP 47, que puedes usar para la calificación de recursos de idiomas y regiones específicos. Una etiqueta de idioma se compone de una secuencia de una o más subetiquetas. Cada una acota o afina la variedad del idioma que identifica la etiqueta general. Para obtener más información sobre etiquetas de idioma, consulta Etiquetas para identificar idiomas.

Para usar una etiqueta de idioma BCP 47, concatena b+ y un código de idioma ISO 639-1 de dos letras, al que puedes agregar dos subetiquetas separadas con +.

La etiqueta de idioma puede cambiar mientras se usa tu aplicación si los usuarios cambian el idioma en la configuración del sistema. Consulta la sección Manejo de cambios en tiempo de ejecución para obtener información sobre la forma en la que esto afecta tu aplicación durante el tiempo de ejecución.

Consulta la sección Localización a fin de obtener una guía completa para localizar tu aplicación a otros idiomas.

También consulta el método getLocales(), que contiene la lista definida de configuraciones regionales. Esta lista incluye la configuración regional principal.

Dirección del diseño	ldrtl
ldltr
La dirección del diseño de tu aplicación. ldrtl significa "dirección del diseño de derecha a izquierda". ldltr significa "dirección del diseño de izquierda a derecha" y es el valor implícito predeterminado.

Esto puede aplicarse a cualquier recurso, como diseños, elementos de diseño o valores.

Por ejemplo, si desearas proporcionar un diseño específico para el idioma árabe y un diseño genérico para cualquier otro idioma "de derecha a izquierda" (como persa o hebreo), tendrías lo siguiente:


res/
    layout/
        main.xml (Default layout)
    layout-ar/
        main.xml (Specific layout for Arabic)
    layout-ldrtl/
        main.xml (Any "right-to-left" language, except
                  for Arabic, because the "ar" language qualifier
                  has a higher precedence.)
Nota: Para habilitar las funciones de derecha a izquierda en tu aplicación, debes establecer supportsRtl en "true" y establecer la targetSdkVersion en 17 o superior.

Se agregó en el nivel de API 17.

smallestWidth	sw<N>dp

Ejemplos:
sw320dp
sw600dp
sw720dp
etc.	
El tamaño fundamental de una pantalla, indicado por la dimensión más corta del área de pantalla disponible. En concreto, el smallestWidth del dispositivo es la parte más corta de la altura y el ancho disponibles de la pantalla (también puede considerarse como "el menor ancho posible" de la pantalla). Puedes usar este calificador para asegurarte de que, independientemente de la orientación actual de la pantalla, tu aplicación tenga, como mínimo, <N> dp de ancho disponible para su IU.

Por ejemplo, si tu diseño requiere que la dimensión más pequeña del área de pantalla sea siempre de al menos 600 dp, puedes usar este calificador para crear los recursos de diseño, res/layout-sw600dp/. El sistema utilizará estos recursos solo cuando la menor dimensión de la pantalla disponible sea de al menos 600 dp, independientemente de que el lado de 600 dp sea la altura o el ancho percibido por el usuario. El smallestWidth es una característica fija de tamaño de pantalla del dispositivo; el smallestWidth del dispositivo no se modifica cuando la orientación de la pantalla cambia.

El uso de smallestWidth para determinar el tamaño general de la pantalla es útil porque el ancho es, a menudo, el factor que impulsa la creación de un diseño. Una IU también se desplazará en sentido vertical, pero tiene restricciones bastante difíciles en el espacio mínimo que requiere en sentido horizontal. El ancho disponible es también el factor clave para determinar si se usará un diseño de un solo panel para los teléfonos móviles o un diseño de varios paneles para las tablets. Por lo tanto, probablemente te preocupe más el ancho mínimo posible de cada dispositivo.

El smallestWidth de un dispositivo tiene en cuenta las decoraciones de la pantalla y la IU del sistema. Por ejemplo, si el dispositivo tiene algunos elementos de IU persistentes en la pantalla que ocupan espacio a lo largo del eje del smallestWidth, el sistema declara que el smallestWidth es menor que el tamaño de pantalla real, porque esos son píxeles de pantalla no disponibles para la IU.

Estos son algunos valores que puedes utilizar para tamaños de pantallas comunes:

320, para dispositivos con configuraciones de pantalla como las siguientes:
240 x 320 ldpi (teléfono celular QVGA)
320 x 480 mdpi (teléfono celular)
480 x 800 hdpi (teléfono celular de alta densidad)
480, para pantallas de 480 x 800 mdpi (tablet/teléfono celular).
600, para pantallas de 600 x 1024 mdpi (tablet de 7 pulgadas).
720, para pantallas de 720 x 1280 mdpi (tablet de 10 pulgadas).
Cuando tu aplicación proporciona directorios de múltiples recursos con valores diferentes para el calificador smallestWidth, el sistema utiliza el más cercano (sin superarlo) al smallestWidth del dispositivo.

Se agregó en el nivel de API 13.

Consulta también el atributo android:requiresSmallestWidthDp, que declara el smallestWidth mínimo con el cual tu aplicación es compatible, y el campo de configuración smallestScreenWidthDp, que contiene el valor smallestWidth del dispositivo.

Para obtener más información sobre el diseño de diferentes pantallas y el uso de este calificador, consulta la guía para desarrolladores Compatibilidad con diferentes pantallas.

Ancho disponible	w<N>dp

Ejemplos:
w720dp
w1024dp
etc.	
Especifica un ancho de pantalla mínimo disponible, en unidades dp, en el que se debe utilizar el recurso (definido por el valor <N>). Este valor de configuración se modifica cuando la orientación cambia entre horizontal y vertical para coincidir con el ancho real actual.

Esto, a menudo, resulta útil para determinar si debe usarse un diseño de varios paneles, ya que, incluso en una tablet, no suele ser conveniente el mismo diseño de varios paneles en las orientaciones vertical y horizontal. Por lo tanto, puedes usar esto para especificar el ancho mínimo que se requiere para el diseño, en lugar de usar el tamaño de pantalla y los calificadores de orientación juntos.

Cuando tu aplicación proporciona directorios de múltiples recursos con valores diferentes para esta configuración, el sistema utiliza el más cercano (sin superarlo) al ancho de pantalla actual del dispositivo. El valor tiene en cuenta las decoraciones de la pantalla; por lo tanto, si el dispositivo tiene algunos elementos de IU persistentes en el borde izquierdo o derecho de la pantalla, utiliza un valor para el ancho que es menor que el tamaño real de la pantalla, y tiene en cuenta estos elementos de IU y reduce el espacio disponible de la aplicación.

Se agregó en el nivel de API 13.

Consulta también el campo de configuración screenWidthDp, que contiene el ancho de pantalla actual.

Para obtener más información sobre el diseño de diferentes pantallas y el uso de este calificador, consulta la guía para desarrolladores Compatibilidad con diferentes pantallas.

Altura disponible	h<N>dp

Ejemplos:
h720dp
h1024dp
etc.	
Especifica una altura de pantalla mínima disponible, en unidades "dp", en la que se debe utilizar el recurso (definido por el valor <N>). Este valor de configuración se modifica cuando la orientación cambia entre horizontal y vertical para coincidir con la altura real actual.

El uso de este calificador para definir la altura que requiere tu diseño tiene la misma utilidad que w<N>dp para definir el ancho requerido, en lugar de usar los calificadores de tamaño y orientación de pantalla. Sin embargo, la mayoría de las aplicaciones no necesitan este tipo de calificadores, considerando que las IU, a menudo, se desplazan en sentido vertical y son más flexibles con la altura disponible, mientras que el ancho es más rígido.

Cuando tu aplicación proporciona directorios de múltiples recursos con valores diferentes para esta configuración, el sistema utiliza el más cercano (sin superarlo) a la altura de pantalla actual del dispositivo. El valor tiene en cuenta las decoraciones de la pantalla; por lo tanto, si el dispositivo tiene algunos elementos de IU persistentes en el borde superior o inferior de la pantalla, utiliza un valor para la altura que es menor que el tamaño real de la pantalla, y tiene en cuenta estos elementos de IU y reduce el espacio disponible de la aplicación. Las decoraciones de pantalla que no son fijas (por ejemplo, una barra de estado que se puede ocultar en el modo de pantalla completa) no se tienen en cuenta en este caso, y tampoco se tienen en cuenta las decoraciones de ventanas, como la barra de título o la barra de acciones, por lo que las aplicaciones deben estar preparadas para utilizar un espacio un poco más pequeño del que especifican.

Se agregó en el nivel de API 13.

Consulta también el campo de configuración screenHeightDp, que contiene el ancho de pantalla actual.

Para obtener más información sobre el diseño de diferentes pantallas y el uso de este calificador, consulta la guía para desarrolladores Compatibilidad con diferentes pantallas.

Tamaño de pantalla	small
normal
large
xlarge	
small: Pantallas que son de tamaño similar a una pantalla QVGA de baja densidad. El tamaño de diseño mínimo de una pantalla pequeña es de aproximadamente 320 x 426 unidades dp. Dos ejemplos son las pantallas QVGA de baja densidad y las pantallas VGA de alta densidad.
normal: Pantallas que son de tamaño similar a una pantalla HVGA de densidad media. El tamaño de diseño mínimo de una pantalla normal es de aproximadamente 320 x 470 unidades dp. Algunos ejemplos de tales pantallas son las pantallas WQVGA de baja densidad, HVGA de densidad media y WVGA de alta densidad.
large: Pantallas que son de tamaño similar a una pantalla VGA de densidad media. El tamaño de diseño mínimo de una pantalla grande es de aproximadamente 480 x 640 unidades dp. Algunos ejemplos son las pantallas VGA y WVGA de densidad media.
xlarge: Pantallas que son considerablemente más grandes que la pantalla HVGA de densidad media tradicional. El tamaño de diseño mínimo de una pantalla extragrande es de aproximadamente 720 x 960 unidades dp. En la mayoría de los casos, los dispositivos con pantallas extragrandes serían demasiado grandes para llevarlos en un bolsillo y probablemente serían dispositivos similares a tablets. Se agregó en el nivel de API 9.
Nota: El uso de un calificador de tamaño no implica que los recursos sean solamente para pantallas de ese tamaño. Si no proporcionas recursos alternativos con calificadores que se ajusten mejor a la configuración actual del dispositivo, es posible que el sistema utilice los recursos que mejor coincidan.

Advertencia: Si todos tus recursos usan un calificador de tamaño mayor que la pantalla actual, el sistema no los usará y tu aplicación se bloqueará en tiempo de ejecución (por ejemplo, si todos los recursos de diseño están etiquetados con el calificador xlarge, pero el dispositivo es una pantalla de tamaño normal).

Se agregó en el nivel de API 4.

Consulta Compatibilidad con diferentes pantallas para obtener más información.

Consulta también el campo de configuración screenLayout, que indica si la pantalla es pequeña, normal o grande.

Aspecto de la pantalla	long
notlong	
long: Pantallas largas, como WQVGA, WVGA, FWVGA.
notlong: Pantallas que no son largas, como QVGA, HVGA y VGA.
Se agregó en el nivel de API 4.

Esto se basa exclusivamente en la relación de aspecto de la pantalla (una pantalla "larga" es más ancha). Este objeto no está relacionado con la orientación de la pantalla.

Consulta también el campo de configuración screenLayout, que indica si la pantalla es larga.

Pantalla circular	round
notround	
round: pantallas circulares; por ejemplo, un dispositivo wearable circular.
notround: pantallas rectangulares, como teléfonos y tablets
Se agregó en el nivel de API 23.

Consulta también el método de configuración isScreenRound(), que indica si la pantalla es circular.

Gamut amplio de colores	widecg
nowidecg	
{@code widecg}: Pantallas con un gamut amplio de colores, como Display P3 o AdobeRGB.
{@code nowidecg}: Pantallas con un gamut limitado de colores, como sRGB.
Se agregó en el nivel de API 26.

Consulta también el método de configuración isScreenWideColorGamut(), que indica si la pantalla tiene un gamut amplio de colores.

Rango dinámico alto (HDR)	highdr
lowdr	
{@code highdr}: Pantallas con un rango dinámico alto.
{@code lowdr}: Pantallas con un rango dinámico bajo o estándar.
Se agregó en el nivel de API 26.

Consulta también el método de configuración isScreenHdr(), que indica si la pantalla tiene capacidades de HDR.

Orientación de la pantalla	port
land	
port: El dispositivo está en orientación vertical.
land: El dispositivo está en orientación horizontal.
Esto puede cambiar durante el ciclo de vida de tu aplicación si el usuario gira la pantalla. Consulta la sección Manejo de cambios en tiempo de ejecución para obtener información sobre la forma en la que esto puede afectar tu aplicación durante el tiempo de ejecución.

Consulta también el campo de configuración orientation, que indica la orientación actual del dispositivo.

Modo de IU	car
desk
television
appliance
watch
vrheadset	
car: El dispositivo se muestra en un conector para autos.
desk: El dispositivo se muestra en un conector para escritorio.
television: El dispositivo se muestra en una televisión y proporciona una experiencia de "diez pies" en la que su IU está en una pantalla grande de la cual el usuario se encuentra alejado, orientada principalmente en torno a un DPAD u otra interacción que no sea de puntero.
appliance: El dispositivo se utiliza como un aparato, sin pantalla.
watch: El dispositivo tiene una pantalla y se usa en la muñeca.
vrheadset: El dispositivo se muestra en auriculares de realidad virtual.
Se agregó en el nivel de API 8; televisión agregada en el nivel de API 13; reloj agregado en el nivel de API 20.

Para obtener información sobre cómo puede responder tu aplicación cuando el dispositivo se inserta en un conector o se retira de este, consulta Cómo determinar y supervisar el tipo y el estado del conector.

Esto puede cambiar durante el ciclo de vida de tu aplicación si el usuario coloca el dispositivo en un conector. Puedes habilitar o inhabilitar algunos de estos modos mediante UiModeManager. Consulta la sección Manejo de cambios en tiempo de ejecución para obtener información sobre la forma en la que esto puede afectar tu aplicación durante el tiempo de ejecución.

Modo nocturno	night
notnight	
night: Noche
notnight: Día
Se agregó en el nivel de API 8.

Esto puede cambiar durante el ciclo de vida de tu aplicación si el modo nocturno se deja en modo automático (predeterminado); en ese caso, el modo cambia según el momento del día. Puedes habilitar o inhabilitar este modo utilizando UiModeManager. Consulta la sección Manejo de cambios en tiempo de ejecución para obtener información sobre la forma en la que esto puede afectar tu aplicación durante el tiempo de ejecución.

Densidad de píxeles de la pantalla (dpi)	ldpi
mdpi
hdpi
xhdpi
xxhdpi
xxxhdpi
nodpi
tvdpi
anydpi
nnndpi	
ldpi: Pantallas de baja densidad; aproximadamente, 120 dpi.
mdpi: Pantallas de densidad media (en HVGA tradicional); aproximadamente, 160 dpi.
hdpi: Pantallas de alta densidad; aproximadamente, 240 dpi.
xhdpi: Pantallas de densidad extraalta; aproximadamente, 320 dpi. Se agregó en el nivel de API 8.
xxhdpi: Pantallas de densidad extraextraalta; aproximadamente, 480 dpi. Se agregó en el nivel de API 16.
xxxhdpi: Usos de densidad extraextraextraalta (ícono de selector solamente, consulta la nota en Compatibilidad con diferentes pantallas); aproximadamente, 640 dpi. Se agregó en el nivel de API 18.
nodpi: Esto se puede utilizar para los recursos de mapas de bits que no deseas que se escalen para que coincidan con la densidad del dispositivo.
tvdpi: Pantallas entre mdpi y hdpi; aproximadamente, 213 dpi. Este grupo no se considera un grupo de densidad "principal". Se usa principalmente para televisiones y la mayoría de las aplicaciones no lo necesitan; con recursos mdpi y hdpi es suficiente para la mayoría de las aplicaciones y el sistema los ajusta según corresponde. Se agregó en el nivel de API 13.
anydpi: Este calificador coincide con todas las densidades de pantalla y tiene prioridad respecto de otros calificadores. Resulta útil para los elementos de diseño vectoriales. Se agregó en el nivel de API 21.
nnndpi: Se usa para representar densidades no estándar, en las que nnn es una densidad de la pantalla representada por un valor entero positivo. No debe usarse en la mayoría de los casos. Es mejor usar depósitos de densidad estándar, que reducen notablemente la sobrecarga que supone admitir la gran variedad de densidades de pantalla de los dispositivos disponibles en el mercado.
Existe una relación de ajuste de escala de 3:4:6:8:12:16 entre las seis densidades principales (si se ignora la densidad tvdpi). Por lo tanto, un mapa de bits de 9 x 9 en ldpi es de 12 x 12 en mdpi, de 18 x 18 en hdpi, de 24 x 24 en xhdpi, etc.

Si decides que tus recursos de imagen no se ven lo suficientemente bien en una televisión o en otros dispositivos determinados, y deseas probar los recursos tvdpi, el factor de escala es 1,33* mdpi. Por ejemplo, una imagen de 100 x 100 px para pantallas mdpi debe ser de 133 x 133 px para tvdpi.

Nota: El uso de un calificador de densidad no implica que los recursos sean solamente para pantallas de esa densidad. Si no proporcionas recursos alternativos con calificadores que se ajusten mejor a la configuración actual del dispositivo, es posible que el sistema utilice los recursos que mejor coincidan.

Consulta la sección Compatibilidad con diferentes pantallas para obtener más información sobre cómo manejar diferentes densidades de pantalla y cómo Android podría escalar tus mapas de bits para adaptarlos a la densidad actual.

Tipo de pantalla táctil	notouch
finger	
notouch: El dispositivo no tiene una pantalla táctil.
finger: El dispositivo tiene una pantalla táctil para utilizar mediante la dirección e interacción del dedo del usuario.
Consulta también el campo de configuración touchscreen, que indica el tipo de pantalla táctil del dispositivo.

Disponibilidad del teclado	keysexposed
keyshidden
keyssoft	
keysexposed: El dispositivo tiene un teclado disponible. Si el dispositivo tiene un teclado de software habilitado (lo cual es probable), este puede utilizarse incluso cuando el teclado de hardware no está expuesto al usuario, aunque el dispositivo no tenga un teclado de hardware. Si no se proporciona un teclado de software, o si está inhabilitado, se utiliza solamente cuando el teclado de hardware está expuesto.
keyshidden: El dispositivo tiene un teclado de hardware disponible, pero está oculto y el dispositivo no tiene un teclado de software habilitado.
keyssoft: El dispositivo tiene un teclado de software habilitado, ya sea visible o no.
Si proporcionas recursos keysexposed, pero no recursos keyssoft, el sistema utiliza los recursos keysexposed independientemente de que un teclado sea visible, siempre que el sistema tenga un teclado de software habilitado.

Esto puede cambiar durante el ciclo de vida de tu aplicación si el usuario abre un teclado de hardware. Consulta la sección Manejo de cambios en tiempo de ejecución para obtener información sobre la forma en la que esto puede afectar tu aplicación durante el tiempo de ejecución.

Consulta también los campos de configuración hardKeyboardHidden y keyboardHidden, que indican la visibilidad de un teclado de hardware y la visibilidad de cualquier clase de teclado (incluido el teclado de software), respectivamente.

Método principal de entrada de texto	nokeys
qwerty
12key	
nokeys: El dispositivo no tiene teclas de hardware para la entrada de texto.
qwerty: El dispositivo tiene un teclado qwerty de hardware, que puede ser visible para el usuario o no.
12key: El dispositivo tiene un teclado de hardware de 12 teclas, que puede ser visible para el usuario o no.
Consulta también el campo de configuración keyboard, que indica el método principal de entrada de texto disponible.

Disponibilidad de las teclas de navegación	navexposed
navhidden	
navexposed: Las teclas de navegación están disponibles para el usuario.
navhidden: Las teclas de navegación no están disponibles (por ejemplo, detrás de una tapa cerrada).
Esto puede cambiar durante el ciclo de vida de tu aplicación si el usuario revela las teclas de navegación. Consulta la sección Manejo de cambios en tiempo de ejecución para obtener información sobre la forma en la que esto puede afectar tu aplicación durante el tiempo de ejecución.

Consulta también el campo de configuración navigationHidden, que indica si las teclas de navegación están ocultas.

Método principal de navegación no táctil	nonav
dpad
trackball
wheel	
nonav: El dispositivo no tiene otro recurso de navegación que no sea el uso de la pantalla táctil.
dpad: El dispositivo tiene un controlador de dirección (o D-Pad) para la navegación.
trackball: El dispositivo tiene una bola de seguimiento para la navegación.
wheel: El dispositivo tiene una rueda de dirección para la navegación (poco común).
Consulta también el campo de configuración navigation, que indica el tipo de método de navegación disponible.

Versión de la plataforma (nivel de API)	Ejemplos:
v3
v4
v7
etc.	
Nivel de API que admite el dispositivo. Por ejemplo, v1 para el nivel de API 1 (dispositivos con Android 1.0 o versiones posteriores) y v4 para el nivel de API 4 (dispositivos con Android 1.6 o versiones posteriores). Consulta el documento Niveles de API de Android para obtener más información sobre estos valores.

Nota: Desde la versión de Android 1.0, se agregaron algunos calificadores de configuración; por lo tanto, no todas las versiones de Android admiten todos los calificadores. Al utilizar un nuevo calificador, se agrega de forma implícita el calificador de la versión de la plataforma, de modo que los dispositivos anteriores lo ignoren. Por ejemplo, al utilizar un calificador w600dp, se incluye automáticamente el calificador v13, porque el calificador de ancho disponible era nuevo en el nivel de API 13. Para evitar problemas, siempre incluye un conjunto de recursos predeterminados (un conjunto de recursos sin calificadores). Para obtener más información, consulta la sección Provisión de la mejor compatibilidad de dispositivos con recursos.

Reglas de nombres de calificadores
A continuación se incluyen algunas reglas acerca del uso de los nombres de calificadores de configuración:

Puedes especificar varios calificadores, separados por guiones, para un solo conjunto de recursos. Por ejemplo, drawable-en-rUS-land se aplica a dispositivos de inglés (Estados Unidos) en orientación horizontal.
Los calificadores deben estar en el orden enumerado en la tabla 2. Por ejemplo:
Incorrecto: drawable-hdpi-port/
Correcto: drawable-port-hdpi/
Los directorios de recursos alternativos no pueden estar anidados. Por ejemplo, no puedes tener res/drawable/drawable-en/.
Los valores no distinguen mayúsculas de minúsculas. Los compiladores de recursos convierten los nombres de directorios a minúsculas antes del procesamiento para evitar problemas en los sistemas de archivos que no distinguen mayúsculas de minúsculas. Cualquier uso de mayúsculas en los nombres es solo para facilitar la lectura.
Se admite un solo valor para cada tipo de calificador. Por ejemplo, si deseas utilizar los mismos archivos de elementos de diseño para España y Francia, no puedes asignar el nombre drawable-rES-rFR/ a un directorio. En cambio, necesitas dos directorios de recursos que contengan los archivos correspondientes, como drawable-rES/ y drawable-rFR/. Sin embargo, no es necesario que realmente dupliques los mismos archivos en ambas ubicaciones. En cambio, puedes crear un alias para un recurso. Consulta la sección Creación de recursos de alias a continuación.
Una vez que guardas los recursos alternativos en directorios denominados con estos calificadores, Android aplica automáticamente los recursos en tu aplicación de acuerdo con la configuración del dispositivo actual. Cada vez que se solicita un recurso, Android busca directorios de recursos alternativos que contengan el archivo de recursos solicitado y, luego, encuentra el recurso de coincidencia óptima (lo que se explica a continuación). Si no existen recursos alternativos que coincidan con una configuración de dispositivo determinada, Android utiliza los recursos predeterminados correspondientes (el conjunto de recursos para un tipo de recurso determinado que no incluye un calificador de configuración).

Creación de recursos de alias
Cuando tienes un recurso que deseas utilizar para más de una configuración de dispositivo (pero no deseas proporcionarlo como un recurso predeterminado), no necesitas colocar el mismo recurso en más de un directorio de recursos alternativos. En cambio, puedes (en algunos casos) crear un recurso alternativo que actúe como un alias para un recurso guardado en su directorio de recursos predeterminados.

Nota: No todos los recursos ofrecen un mecanismo mediante el cual puedas crear un alias para otro recurso. En particular, los recursos de animación, de menú, sin procesar y de otro tipo no especificados del directorio xml/ no ofrecen esta función.

Por ejemplo, imagina que tienes un ícono de aplicación, icon.png, y necesitas una versión única de dicho ícono para diferentes configuraciones regionales. Sin embargo, dos configuraciones regionales, Inglés (Canadá) y Francés (Canadá), necesitan utilizar la misma versión. Quizá supongas que debes copiar la misma imagen en el directorio de recursos tanto para Inglés (Canadá) como para Francés (Canadá), pero no es así. En cambio, puedes guardar la imagen que se usa para ambos como icon_ca.png (cualquier nombre que no sea icon.png) y disponerla en el directorio res/drawable/ predeterminado. Luego, crea un archivo icon.xml en res/drawable-en-rCA/ y res/drawable-fr-rCA/ que haga referencia al recurso icon_ca.png mediante el elemento <bitmap>. Esto te permite almacenar una sola versión del archivo PNG y dos archivos XML pequeños que apuntan a dicho archivo PNG. A continuación, se muestra un ejemplo con un archivo XML.

Elemento de diseño
A fin de crear un alias para un elemento de diseño existente, usa el elemento <drawable>. Por ejemplo:


<?xml version="1.0" encoding="utf-8"?>
<resources>
    <drawable name="icon">@drawable/icon_ca</drawable>
</resources>
Si guardas este archivo como drawables.xml (en un directorio de recursos alternativos, como res/values-en-rCA/), se compila en un recurso al cual puedes hacer referencia como R.drawable.icon, pero en realidad es un alias del recurso R.drawable.icon_ca (guardado en res/drawable/).

Diseño
A fin de crear un alias para un diseño existente, usa el elemento <include> dentro de un <merge>. Por ejemplo:


<?xml version="1.0" encoding="utf-8"?>
<merge>
    <include layout="@layout/main_ltr"/>
</merge>
Si guardas este archivo como main.xml, se compila en un recurso al cual puedes hacer referencia como R.layout.main, pero, en realidad, es un alias del recurso R.layout.main_ltr.

Strings y otros valores simples
A fin de crear un alias para una string existente, simplemente utiliza el ID de recurso de la string deseada como el valor de la nueva string. Por ejemplo:


<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="hello">Hello</string>
    <string name="hi">@string/hello</string>
</resources>
El recurso R.string.hi ahora es un alias para R.string.hello.

Otros valores simples funcionan de la misma manera. Por ejemplo, un color:


<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="red">#f00</color>
    <color name="highlight">@color/red</color>
</resources>
Cómo acceder a los recursos de tu aplicación
Una vez que proporciones un recurso en tu aplicación, puedes aplicarlo haciendo referencia a su ID de recurso. Todos los ID de recursos se definen en la clase R de tu proyecto, que la herramienta aapt genera automáticamente.

Cuando se compila tu aplicación, aapt genera la clase R, que contiene los ID de recurso de todos los recursos de tu directorio res/. Para cada tipo de recurso hay una subclase R (por ejemplo, R.drawable para todos los recursos de elementos de diseño), y para cada recurso de ese tipo hay un valor entero estático (por ejemplo, R.drawable.icon). Ese valor entero es el ID del recurso que puedes usar para recuperar tu recurso.

Si bien en la clase R se especifican los ID de recursos, no necesitas buscar en ella para hallar uno. El ID de recurso siempre está compuesto por lo siguiente:

El tipo de recurso: Cada recurso se agrupa en un “tipo”, como string, drawable y layout. Para obtener más información acerca de los diferentes tipos, consulta Tipos de recursos.
El nombre del recurso, que es el nombre de archivo sin la extensión o el valor en el atributo XML android:name si el recurso es un valor simple (como una string).
Existen dos maneras de acceder a un recurso:

En código: Usando un valor entero de una subclase de tu clase R; por ejemplo:

R.string.hello
string es el tipo de recurso y hello es el nombre del recurso. Hay muchas API de Android que pueden acceder a tus recursos cuando proporcionas un ID de recurso en este formato. Consulta Acceso a recursos en código.

En XML: Usando una sintaxis XML que también corresponde al ID de recurso definido en tu clase R; por ejemplo:

@string/hello
string es el tipo de recurso y hello es el nombre del recurso. Puedes usar esta sintaxis en un recurso XML en cualquier lugar donde esté previsto que proporciones un valor en un recurso. Consulta Acceso a recursos desde XML.

Acceso a recursos en código
Puedes usar un recurso en código al pasar el ID de recurso como un parámetro del método. Por ejemplo, puedes establecer una ImageView para usar el recurso res/drawable/myimage.png con setImageResource():

KOTLIN
JAVA

val imageView = findViewById(R.id.myimageview) as ImageView
imageView.setImageResource(R.drawable.myimage)
También puedes recuperar recursos individuales con métodos en Resources, del cual puedes obtener una instancia con getResources().

Sintaxis
Esta es la sintaxis que necesitas para hacer referencia a un recurso en código:


[<package_name>.]R.<resource_type>.<resource_name>
<package_name> es el nombre del paquete en el que se ubica el recurso (no es necesario cuando haces referencia a recursos desde tu paquete).
<resource_type> es la subclase R para el tipo de recurso.
<resource_name> es el nombre de archivo del recurso sin la extensión o el valor del atributo android:name en el elemento XML (para valores simples).
Consulta Tipos de recursos para obtener más información acerca de cada tipo de recurso y cómo hacer referencia a ellos.

Casos de uso
Hay muchos métodos que aceptan un parámetro de ID de recurso y puedes recuperar recursos usando métodos en Resources. Puedes obtener una instancia de Resources con Context.getResources().

Aquí te mostramos algunos ejemplos de acceso a recursos en código:

KOTLIN
JAVA

// Load a background for the current screen from a drawable resource
window.setBackgroundDrawableResource(R.drawable.my_background_image)

// Set the Activity title by getting a string from the Resources object, because
//  this method requires a CharSequence rather than a resource ID
window.setTitle(resources.getText(R.string.main_title))

// Load a custom layout for the current screen
setContentView(R.layout.main_screen)

// Set a slide in animation by getting an Animation from the Resources object
flipper.setInAnimation(AnimationUtils.loadAnimation(this,
        R.anim.hyperspace_in))

// Set the text on a TextView object using a resource ID
val msgTextView = findViewById(R.id.msg) as TextView
msgTextView.setText(R.string.hello_message)
Advertencia: Nunca debes modificar el archivo R.java manualmente; se genera a través de la herramienta aapt cuando se compila tu proyecto. Los cambios se invalidarán la próxima vez que compiles.

Acceso a recursos desde XML
Puedes definir valores para algunos atributos y elementos XML usando una referencia a un recurso existente. Generalmente, harás esto cuando crees archivos de diseño a fin de proporcionar strings e imágenes para tus widgets.

Por ejemplo, si agregas un Button a tu diseño, debes usar un recurso de string para el texto del botón:


<Button
    android:layout_width="fill_parent"
    android:layout_height="wrap_content"
    android:text="@string/submit" />
Sintaxis
Esta es la sintaxis que necesitas para hacer referencia a un recurso en un recurso XML:


@[<package_name>:]<resource_type>/<resource_name>
<package_name> es el nombre del paquete en el que se ubica el recurso (no es necesario cuando se hace referencia a recursos desde el mismo paquete).
<resource_type> es la subclase R para el tipo de recurso.
<resource_name> es el nombre de archivo del recurso sin la extensión o el valor del atributo android:name en el elemento XML (para valores simples).
Consulta Tipos de recursos para obtener más información acerca de cada tipo de recurso y cómo hacer referencia a ellos.

Casos de uso
En algunos casos, debes usar un recurso para un valor en XML (por ejemplo, para aplicar una imagen de elemento de diseño a un widget), pero también puedes usar un recurso en XML en cualquier lugar donde se acepte un valor simple. Por ejemplo, si tienes el siguiente archivo de recurso que incluye un recurso de color y un recurso de string:


<?xml version="1.0" encoding="utf-8"?>
<resources>
   <color name="opaque_red">#f00</color>
   <string name="hello">Hello!</string>
</resources>
Puedes usar estos recursos en el siguiente archivo de diseño para establecer el color del texto y la string de texto:


<?xml version="1.0" encoding="utf-8"?>
<EditText xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:textColor="@color/opaque_red"
    android:text="@string/hello" />
En este caso, no necesitas especificar el nombre del paquete en la referencia del recurso porque los recursos son de tu propio paquete. Para hacer referencia a un recurso del sistema, debes incluir el nombre del paquete. Por ejemplo:


<?xml version="1.0" encoding="utf-8"?>
<EditText xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:textColor="@android:color/secondary_text_dark"
    android:text="@string/hello" />
Nota: Puedes usar recursos de strings en todo momento para localizar tu aplicación a otros idiomas. Para obtener información acerca de cómo crear recursos alternativos (como strings localizadas), consulta Provisión de recursos alternativos. Para acceder a una guía completa acerca de cómo localizar tu aplicación a otros idiomas, consulta Localización.

Incluso puedes usar recursos en XML para crear alias. Por ejemplo, puedes crear un elemento de diseño que sea un alias para otro recurso de elemento de diseño:


<?xml version="1.0" encoding="utf-8"?>
<bitmap xmlns:android="http://schemas.android.com/apk/res/android"
    android:src="@drawable/other_drawable" />
Esto suena redundante, pero puede ser muy útil cuando se usa un recurso alternativo. Obtén más información sobre la Creación de recursos de alias.

Referencia a atributos de estilo
Un recurso de atributo de estilo te permite hacer referencia al valor de un atributo en el tema aplicado actualmente. Hacer referencia a un atributo de estilo te permite personalizar la apariencia de elementos de la IU al diseñarlos para que coincidan con variaciones estándar proporcionadas por el tema actual, en lugar de proporcionar un valor hard-coded. Hacer referencia a un atributo de estilo esencialmente significa "usar el estilo identificado por el atributo en el tema actual".

Para hacer referencia a un atributo de estilo, la sintaxis del nombre es prácticamente idéntica al formato de recurso normal, pero, en lugar del símbolo de arroba (@), usa un signo de interrogación (?). La sección de tipo de recurso es opcional. Por ejemplo:


?[<package_name>:][<resource_type>/]<resource_name>
Así es como puedes hacer referencia a un atributo para establecer el color de texto de modo que coincida con el color de texto "primario" del tema del sistema:


<EditText id="text"
    android:layout_width="fill_parent"
    android:layout_height="wrap_content"
    android:textColor="?android:textColorSecondary"
    android:text="@string/hello_world" />
Aquí, el atributo android:textColor especifica el nombre de un atributo de estilo en el tema actual. Android ahora usa el valor aplicado al atributo de estilo android:textColorSecondary como el valor para android:textColor en este widget. Debido a que la herramienta de recursos del sistema reconoce que se prevé un recurso de atributo en este contexto, no necesitas indicar explícitamente el tipo (que sería ?android:attr/textColorSecondary); puedes excluir el tipo attr.

Acceso a archivos originales
Si bien es poco común, podrías necesitar acceder a tus archivos y directorios originales. Si es así, no podrás guardar tus archivos en res/, ya que el único elemento que permite leer un recurso desde res/ es el ID de recurso. Como alternativa, puedes guardar tus recursos en el directorio assets/.

Los archivos guardados en el directorio assets/ no reciben un ID de recurso, por lo que no puedes hacer referencia a ellos a través de la clase R ni desde recursos XML. En cambio, puedes consultar archivos en el directorio assets/ como en un sistema de archivos normal y leer datos sin procesar usando AssetManager.

No obstante, si lo único que necesitas es la capacidad de leer datos sin procesar (como un archivo de audio o video), guarda el archivo en el directorio res/raw/ y lee un flujo de bytes usando openRawResource().

Acceso a recursos de la plataforma
Android contiene una cantidad de recursos estándar, como estilos, temas y diseños. Para acceder a esos recursos, califica la referencia a tu recurso con el nombre de paquete android. Por ejemplo, Android proporciona un recurso de diseño que puedes usar para enumerar elementos en un ListAdapter:

KOTLIN
JAVA

listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, myarray)
En este ejemplo, simple_list_item_1 es un recurso de diseño definido por la plataforma para elementos en una ListView. Puedes usar esto en lugar de crear tu propio diseño para los elementos de la lista.

Provisión de la mejor compatibilidad de dispositivos con recursos
Para que tu aplicación admita varias configuraciones de dispositivos, es muy importante que siempre proporciones recursos predeterminados para cada tipo de recurso utilizado por tu aplicación.

Por ejemplo, si tu aplicación admite varios idiomas, incluye siempre un directorio values/ (en el cual se guardan tus strings) sin un calificador de idioma y región. En cambio, si colocas todos tus archivos de strings en directorios que tienen un calificador de idioma y región, tu aplicación fallará cuando se ejecute en un dispositivo configurado en un idioma que tus strings no admitan. Sin embargo, siempre que proporciones recursos values/ predeterminados, tu aplicación se ejecutará correctamente aunque el usuario no comprenda ese idioma (es preferible esto a un bloqueo).

De la misma manera, si proporcionas diferentes recursos de diseño de acuerdo con la orientación de la pantalla, debes elegir una orientación como predeterminada. Por ejemplo, en lugar de proporcionar recursos de diseño en layout-land/ para la orientación horizontal y layout-port/ para la orientación vertical, deja uno como predeterminado, como layout/ para la orientación horizontal y layout-port/ para la orientación vertical.

Proporcionar recursos predeterminados es importante no solo porque tu aplicación podría ejecutarse en una configuración que no habías previsto, sino también porque las nuevas versiones de Android a veces agregan calificadores de configuración que las versiones anteriores no admiten. Si utilizas un nuevo calificador de recursos, pero mantienes la compatibilidad del código con versiones anteriores de Android, cuando una versión anterior de Android ejecute tu aplicación, esta fallará si no proporcionas recursos predeterminados, ya que no podrá utilizar los recursos denominados con el nuevo calificador. Por ejemplo, si tu minSdkVersion se establece en 4, y calificas todos tus recursos de elemento de diseño con el modo nocturno (night o notnight, que se agregaron en el nivel de API 8), un dispositivo con nivel de API 4 no podrá acceder a tus recursos de elementos de diseño y se bloqueará. En este caso, probablemente desees que notnight sean tus recursos predeterminados, por lo cual debes excluir ese calificador para que tus recursos de elemento de diseño estén en drawable/ o drawable-night/.

Por lo tanto, a fin de ofrecer la mejor compatibilidad de dispositivo, siempre proporciona recursos predeterminados para los recursos que tu aplicación necesita para funcionar correctamente. Luego, crea recursos alternativos para configuraciones de dispositivos específicos utilizando los calificadores de configuración.

Esta regla tiene una excepción. Si la minSdkVersion de tu aplicación es 4 o superior, no necesitas recursos de elementos de diseño predeterminados cuando proporcionas recursos de elementos de diseño alternativos con el calificador de densidad de pantalla. Incluso sin recursos de elementos de diseño predeterminados, Android puede encontrar la mejor coincidencia entre las densidades de pantalla alternativas y escalar el mapa de bits si es necesario. Sin embargo, a fin de brindar la mejor experiencia en todos los tipos de dispositivos, debes proporcionar elementos de diseño alternativos para los tres tipos de densidad.

Cómo encuentra Android el recurso de coincidencia óptima
Cuando solicitas un recurso para el cual proporcionas alternativas, Android selecciona qué recurso alternativo utilizar en tiempo de ejecución, según la configuración del dispositivo actual. Para demostrar cómo Android selecciona un recurso alternativo, imagina que cada uno de los siguientes elementos de diseño contienen versiones diferentes de las mismas imágenes:


drawable/
drawable-en/
drawable-fr-rCA/
drawable-en-port/
drawable-en-notouch-12key/
drawable-port-ldpi/
drawable-port-notouch-12key/
Además, imagina que la configuración del dispositivo es la siguiente:

Configuración regional = en-GB
Orientación de la pantalla = port
Densidad de píxeles de la pantalla = hdpi
Tipo de pantalla táctil = notouch
Método principal de entrada de texto = 12key

Al comparar la configuración del dispositivo con los recursos alternativos disponibles, Android selecciona elementos de diseño de drawable-en-port.

Para decidir qué recursos utilizar, el sistema se basa en la siguiente lógica:


Figura 2: Diagrama de flujo del método que usa Android para encontrar el recurso de coincidencia óptima

Eliminar los archivos de recursos que se contradicen con la configuración del dispositivo.
El directorio drawable-fr-rCA/ se elimina porque se contradice con la configuración regional en-GB.


drawable/
drawable-en/
drawable-fr-rCA/
drawable-en-port/
drawable-en-notouch-12key/
drawable-port-ldpi/
drawable-port-notouch-12key/
Excepción: La densidad de píxeles de la pantalla es el único calificador que no se elimina debido a una contradicción. Aunque la densidad de la pantalla del dispositivo es hdpi, drawable-port-ldpi/ no se elimina porque todas las densidades de pantalla se consideran como una coincidencia en este punto. En el documento Compatibilidad con diferentes pantallas, se incluye más información.

Elegir el (próximo) calificador de mayor precedencia de la lista (tabla 2). (Comienza con MCC y continúa en forma descendente).
¿Alguno de los directorios de recursos incluye este calificador?
Si la respuesta es no, volver al paso 2 y examinar el siguiente calificador. (En el ejemplo, la respuesta es "no" hasta que se alcanza el calificador de idioma).
Si la respuesta es sí, continuar con el paso 4.
Eliminar directorios de recursos que no incluyen este calificador. En el ejemplo, el sistema elimina todos los directorios que no incluyen un calificador de idioma:

drawable/
drawable-en/
drawable-en-port/
drawable-en-notouch-12key/
drawable-port-ldpi/
drawable-port-notouch-12key/
Excepción: Si el calificador en cuestión es la densidad de píxeles de la pantalla, Android selecciona la opción que más coincide con la densidad de la pantalla del dispositivo. En general, Android prefiere reducir una imagen original más grande que ampliar una imagen original más pequeña. Consulta Compatibilidad con diferentes pantallas.

Volver y repetir los pasos 2, 3 y 4 hasta que solo quede un directorio. En el ejemplo, la orientación de la pantalla es el próximo calificador para el cual existen coincidencias. Por lo tanto, se eliminan los recursos que no especifican una orientación de pantalla:

drawable-en/
drawable-en-port/
drawable-en-notouch-12key/
El directorio que queda es drawable-en-port.

Si bien este procedimiento se ejecuta para cada recurso solicitado, el sistema optimiza aún más algunos aspectos. Un ejemplo de esta optimización es que, una vez que se conoce la configuración del dispositivo, el sistema podría eliminar los recursos alternativos que nunca coinciden. Por ejemplo, si el idioma de configuración es inglés ("en"), los directorios de recursos que tienen un calificador de idioma establecido en otro idioma que no sea inglés nunca se incluyen en el conjunto de recursos comprobados (sin embargo, un directorio de recursos sin el calificador de idioma sí se incluye).

Cuando se seleccionan recursos según los calificadores del tamaño de la pantalla, el sistema utiliza los recursos diseñados para una pantalla más pequeña que la pantalla actual si no existen recursos que coincidan mejor (por ejemplo, una pantalla de tamaño grande utiliza recursos de una pantalla de tamaño normal si es necesario). Sin embargo, si los únicos recursos disponibles presentan un tamaño superior al de la pantalla actual, el sistema no los usa y tu aplicación falla si ningún otro recurso coincide con la configuración del dispositivo (por ejemplo, si todos los recursos de diseño están etiquetados con el calificador xlarge, pero el dispositivo tiene una pantalla de tamaño normal).

Nota: La precedencia del calificador (en la tabla 2) es más importante que la cantidad de calificadores que coinciden exactamente con el dispositivo. Por ejemplo, en el paso 4 mencionado anteriormente, la última opción de la lista incluye tres calificadores que coinciden exactamente con el dispositivo (orientación, tipo de pantalla táctil y método de entrada), mientras que drawable-en tiene un solo parámetro que coincide (idioma). Sin embargo, el idioma tiene mayor precedencia que estos otros calificadores; por lo tanto, drawable-port-notouch-12key se elimina.
