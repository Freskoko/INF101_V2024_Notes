package no.uib.inf101;

/**
 * Boolean variables to give or not give consent for the use of semesteroppgave 2 in teaching of
 * computer science to future studenents at the Department of Informatics, University of Bergen.
 *
 * <p>The video sharing consent is only applicable if you produced a video for semesteroppgave 2.
 */
public class Consent {

  /** I give consent that my name is published along side this application (and video). */
  public static final boolean SHARE_NAME = true;

  /**
   * I give consent that this application is used for teaching of computer science at the Department
   * of Informatics, University of Bergen.
   */
  public static final boolean SHARE_APPLICATION = true;

  /**
   * I give consent that the video produced along side this application is used for teaching of
   * computer science at the Department of Informatics, University of Bergen.
   */
  public static final boolean SHARE_VIDEO = true;
}
